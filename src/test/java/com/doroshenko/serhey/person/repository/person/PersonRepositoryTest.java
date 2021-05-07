package com.doroshenko.serhey.person.repository.person;

import com.doroshenko.serhey.person.core.annotation.NoOpSql;
import com.doroshenko.serhey.person.domain.person.Person;
import com.doroshenko.serhey.person.enumeration.person.Gender;
import com.doroshenko.serhey.person.enumeration.person.PersonType;
import com.doroshenko.serhey.person.repository.core.BaseDataJpaTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

@Sql({"classpath:fixture/person/person_insert.sql"})
@Sql(executionPhase = AFTER_TEST_METHOD, scripts = {"classpath:fixture/person/person_truncate.sql"})
class PersonRepositoryTest extends BaseDataJpaTest {

    @PersistenceContext
    EntityManager em;
    @Autowired
    PersonRepository personRepository;

    @Test
    @NoOpSql
    void findOneNullTest() {
        Assertions.assertNull(personRepository.findById(100L).orElse(null));
    }

    @Test
    void findOneNotNullTest() {
        final Person person = personRepository.findById(100L).orElse(null);
        Assertions.assertNotNull(person);
        Assertions.assertNotNull(person.getPersonTypes());
    }

    @Test
    void findAllTest() {
        final List<Person> all = personRepository.findAll();
        Assertions.assertFalse(all.isEmpty());
        all.forEach(person -> Assertions.assertNotNull(person.getPersonTypes()));
    }

    @Test
    @Transactional
    void findAllJpaCriteriaTest() {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Person> query = cb.createQuery(Person.class);
        final Root<Person> root = query.from(Person.class);
        final List<Person> people = em.createQuery(query.select(root)).getResultList();
        Assertions.assertFalse(people.isEmpty());
        people.forEach(person -> Assertions.assertNotNull(person.getPersonTypes()));
    }

    @Test
    void findAllJPQLTest() {
        final String query = "select p from Person p";
        final List<Person> people = em.createQuery(query, Person.class).getResultList();
        people.forEach(person -> Assertions.assertNotNull(person.getPersonTypes()));
    }

    @Test
    void findAllPageableTest() {
        final Page<Person> page = personRepository.findAll(PageRequest.of(0, 20));
        Assertions.assertTrue(page.hasContent());
        final List<Person> content = page.getContent();
        Assertions.assertFalse(content.isEmpty());
        content.forEach(person -> Assertions.assertNotNull(person.getPersonTypes()));
    }

    @Test
    @NoOpSql
    void saveTest() {
        final Person person = new Person();
        person.setGender(Gender.MALE);
        person.setLastName("LastName1");
        person.setFirstName("FirstName1");
        person.setMiddleName("MiddleName1");
        person.setBirthDay(LocalDate.now());
        person.setPersonTypes(new PersonType[]{PersonType.ATHLETE, PersonType.REFEREE});
        final Person savedPerson = personRepository.save(person);
        Assertions.assertNotNull(savedPerson);
        Assertions.assertNotNull(savedPerson.getId());
    }

}
