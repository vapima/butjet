package ru.vapima.butjet.butJet3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vapima.butjet.butJet3.model.Person;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByMail(String mail);
}
