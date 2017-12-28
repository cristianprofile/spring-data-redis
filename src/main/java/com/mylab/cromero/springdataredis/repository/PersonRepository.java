package com.mylab.cromero.springdataredis.repository;

import com.mylab.cromero.springdataredis.domain.Person;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends Repository<Person, String> {

    Optional<Person> findOne(String id);

    List<Person> findByFirstname(String firstname);

    Person save(Person person);

    Iterable<Person> findAll();

    void delete(String id);

    void delete(Person var1);

    void delete(Iterable<Person> people);

    void deleteAll();

    long count();




}
