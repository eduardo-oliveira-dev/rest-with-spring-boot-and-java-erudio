package com.eduardooliveira.service;

import com.eduardooliveira.model.Person;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public List<Person> findAll() {
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 8; i++){
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }

    public Person findById(String id) {
        logger.info("Finding one Person!");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Eduardo");
        person.setLastName("Oliveira");
        person.setAddress("Campina Grande - Paraíba");
        person.setGender("M");
        return person;
    }

    public Person create(Person person) {
        logger.info("Creating Person!");
        return person;
    }

    public Person update(Person person) {
        logger.info("Updating Person!");
        return person;
    }

    public void delete(String id) {
        logger.info("Deleting Person!");
    }

    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Firstname" + i);
        person.setLastName("Lastname" + i);
        person.setAddress("Adres" + i);
        person.setGender("M");
        return person;
    }
}
