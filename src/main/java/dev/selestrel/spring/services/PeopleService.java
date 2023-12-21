package dev.selestrel.spring.services;

import dev.selestrel.spring.models.Person;
import dev.selestrel.spring.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findById(int id) {
        Person person = peopleRepository.findById(id).orElse(null);

        if (person != null) {
            Hibernate.initialize(person.getBooks());
        }

        return person;
    }

    public Person findByName(String name) {

        return peopleRepository.findByName(name);
    }



    @Transactional()
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional()
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional()
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }
}
