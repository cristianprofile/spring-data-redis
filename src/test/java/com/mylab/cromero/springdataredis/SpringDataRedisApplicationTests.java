package com.mylab.cromero.springdataredis;

import com.mylab.cromero.springdataredis.domain.Address;
import com.mylab.cromero.springdataredis.domain.Person;
import com.mylab.cromero.springdataredis.repository.PersonRepository;
import com.mylab.cromero.springdataredis.repository.PersonScript;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.embedded.RedisServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataRedisApplicationTests {


    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonScript personScript;

    private static RedisServer redisServer;

    @BeforeClass
    public static void stopRedisInmemory() throws IOException {
        redisServer = new RedisServer(32771);
        redisServer.start();
    }

    @AfterClass
    public static void startRedisInmemory() throws IOException {

        redisServer.stop();
    }



    @Test
    public void findByFirsName() {

        List<Person> people = personRepository.findByFirstname("Cristian");

        assertThat(people).isEmpty();

        Person person = new Person();
        person.setFirstname("Cristian");
        person.setLastname("Romero");
        List<Address> addresses = new ArrayList<Address>();

        Address address = new Address();
        address.setCity("Madrid");
        address.setStreet("Gran via");
        address.setZipCode("289156");
        addresses.add(address);

        person.setAdress(addresses);

        //Insert new Person
        Person savedPerson = personRepository.save(person);
        assertThat(savedPerson.getId()).isNotNull();

        people = personRepository.findByFirstname("Cristian");

        assertThat(people).isNotEmpty().hasSize(1);

    }




    @Test
    public void testScript() {


        //Script return true when we call more than 3 times
        Boolean key1 = personScript.isMoreThanThree("key1");
        assertThat(key1).isFalse();
        key1 = personScript.isMoreThanThree("key1");
        assertThat(key1).isFalse();
        key1 = personScript.isMoreThanThree("key1");
        assertThat(key1).isFalse();

        //Script return true when we call more than 3 times
        key1 = personScript.isMoreThanThree("key1");
        assertThat(key1).isTrue();

        key1 = personScript.isMoreThanThree("key1");
        assertThat(key1).isTrue();

    }



    @Test
    public void testRepositoryMethods() {

        Optional<Person> findedPerson = personRepository.findOne("123");
        assertThat(findedPerson).isEmpty();

        Person person = new Person();
        person.setFirstname("Cristian");
        person.setLastname("Romero");
        List<Address> addresses = new ArrayList<Address>();

        Address address = new Address();
        address.setCity("Madrid");
        address.setStreet("Gran via");
        address.setZipCode("289156");
        addresses.add(address);

        person.setAdress(addresses);

        //Insert new Person
        Person savedPerson = personRepository.save(person);
        assertThat(savedPerson.getId()).isNotNull();

        findedPerson = personRepository.findOne(savedPerson.getId());
        assertThat(findedPerson).isNotEmpty();

        Iterable<Person> all = personRepository.findAll();

        assertThat(all).hasSize(1);

        //Insert another  Person
        person = new Person();
        person.setFirstname("Sergio");
        person.setLastname("Rodriguez");
        addresses = new ArrayList<Address>();

        address = new Address();
        address.setCity("Barcelona");
        address.setStreet("Gran via");
        address.setZipCode("229156");
        addresses.add(address);

        person.setAdress(addresses);

        savedPerson = personRepository.save(person);

        all = personRepository.findAll();

        assertThat(all).hasSize(2);

        personRepository.delete(savedPerson.getId());


        all = personRepository.findAll();

        assertThat(all).hasSize(1);


        all = personRepository.findAll();

        personRepository.deleteAll();

        all = personRepository.findAll();

        assertThat(all).hasSize(0);
        assertThat(personRepository.count()).isEqualTo(0);



    }

}
