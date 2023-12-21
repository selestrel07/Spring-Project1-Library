package dev.selestrel.spring.repositories;

import dev.selestrel.spring.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

    Person findByName(String name);
}
