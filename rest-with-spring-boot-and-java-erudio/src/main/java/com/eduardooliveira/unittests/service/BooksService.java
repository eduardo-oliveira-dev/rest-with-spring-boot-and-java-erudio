package com.eduardooliveira.service;

import com.eduardooliveira.controller.BooksController;
import com.eduardooliveira.dto.BooksDTO;
import com.eduardooliveira.exception.RequiredObjectIsNullException;
import com.eduardooliveira.exception.ResourceNotFoundException;
import com.eduardooliveira.model.Books;
import com.eduardooliveira.repository.BooksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.eduardooliveira.mapper.ObjectMapper.parseListObjects;
import static com.eduardooliveira.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BooksService {

    private Logger logger = LoggerFactory.getLogger(BooksService.class.getName());

    @Autowired
    BooksRepository booksRepository;

    public List<BooksDTO> findAll() {
        logger.info("Finding all Books!");

        var books = parseListObjects(booksRepository.findAll(), BooksDTO.class);
        books.forEach(BooksService::addHateoasLinks);
        return books;
    }

    public BooksDTO findById(Long id) {
        logger.info("Finding Books by ID!");
        var entity =  booksRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with ID " + id + " not found!"));
        var dto = parseObject(entity, BooksDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public BooksDTO create(BooksDTO booksDTO) {
        if(booksDTO == null) throw new RequiredObjectIsNullException();

        logger.info("Creating a Book!");

        var entity =  parseObject(booksDTO, Books.class);
        var dto = parseObject(booksRepository.save(entity), BooksDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public BooksDTO update(BooksDTO book) {

        if(book == null) throw new RequiredObjectIsNullException();


        logger.info("Updating Book!");
        Books entity = booksRepository.findById(book.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No record found for this ID!"));
        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());

        var dto = parseObject(booksRepository.save(entity), BooksDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id) {
        logger.info("Deleting Book!");
        Books entity = booksRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No record found for this ID!"));
        booksRepository.delete(entity);
    }

    private static void addHateoasLinks(BooksDTO dto) {
        dto.add(linkTo(methodOn(BooksController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(BooksController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(BooksController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(BooksController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(BooksController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));

    }


}
