package com.eduardooliveira.unittests.mocks;

import com.eduardooliveira.dto.BooksDTO;
import com.eduardooliveira.model.Books;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MockBooks {


    public Books mockEntity() {
        return mockEntity(0);
    }

    public BooksDTO mockDTO() {
        return mockDTO(0);
    }

    public List<Books> mockEntityList() {
        List<Books> books = new ArrayList<Books>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BooksDTO> mockDTOList() {
        List<BooksDTO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockDTO(i));
        }
        return books;
    }

    public Books mockEntity(Integer number) {
        Books book = new Books();
        book.setId(number.longValue());
        book.setAuthor("Some Author" + number);
        book.setLaunchDate(LocalDateTime.now());
        book.setTitle("Some Title" + number);
        book.setPrice(new BigDecimal("25.00"));
        return book;
    }

    public BooksDTO mockDTO(Integer number) {
        BooksDTO book = new BooksDTO();
        book.setId(number.longValue());
        book.setAuthor("Some Author" + number);
        book.setLaunchDate(LocalDateTime.now());
        book.setTitle("Some Title" + number);
        book.setPrice(new BigDecimal("25.00"));
        return book;
    }

}