package com.eduardooliveira.repository;

import com.eduardooliveira.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Books, Long> {
}
