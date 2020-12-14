package ru.vapima.butjet.butJet3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vapima.butjet.butJet3.model.AccRec;

import java.util.List;


@Repository
public interface AccRecRepository extends JpaRepository<AccRec, Long> {
    List<AccRec> findAllByAcc_Id(Long id);
}
