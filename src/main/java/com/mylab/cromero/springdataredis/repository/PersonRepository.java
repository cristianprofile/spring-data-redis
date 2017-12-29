package com.mylab.cromero.springdataredis.repository;

import com.mylab.cromero.springdataredis.domain.Person;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends Repository<Person, String> {

    Optional<Person> findOne(String id);



    // it is only allowed to use properties with @Indexed annotation.
    // Please make sure properties used in finder methods are set up for indexing.
    List<Person> findByFirstnameAndPhone_Number(String firstname,String phoneNumber);


    List<Person> findByFirstname(String firstname);

    Person save(Person person);

    Iterable<Person> findAll();

    void delete(String id);

    void delete(Person var1);

    void delete(Iterable<Person> people);

    void deleteAll();

    long count();




}
