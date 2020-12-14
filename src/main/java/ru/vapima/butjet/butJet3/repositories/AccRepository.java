package ru.vapima.butjet.butJet3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vapima.butjet.butJet3.model.Acc;
import ru.vapima.butjet.butJet3.model.Plan;

import java.util.List;


@Repository
public interface AccRepository extends JpaRepository<Acc, Long> {
    List<Acc> findAllByPerson_id(Long id);
}
