package com.eduardooliveira.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class BooksDTO extends RepresentationModel<BooksDTO> {

    private Long id;

    private String author;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
    private LocalDateTime launchDate;

    private BigDecimal price;

    private String title;

    public BooksDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDateTime launchDate) {
        this.launchDate = launchDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BooksDTO booksDTO)) return false;
        return Objects.equals(getId(), booksDTO.getId()) && Objects.equals(getAuthor(), booksDTO.getAuthor()) && Objects.equals(getLaunchDate(), booksDTO.getLaunchDate()) && Objects.equals(getPrice(), booksDTO.getPrice()) && Objects.equals(getTitle(), booksDTO.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAuthor(), getLaunchDate(), getPrice(), getTitle());
    }
}
