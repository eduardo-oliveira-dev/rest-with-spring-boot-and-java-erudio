package com.eduardooliveira.repository;

import com.eduardooliveira.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
