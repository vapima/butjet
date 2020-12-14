package ru.vapima.butjet.butJet3.service;

import ru.vapima.butjet.butJet3.dto.accrec.AccRecDtoAdd;
import ru.vapima.butjet.butJet3.dto.accrec.AccRecDtoResponse;
import ru.vapima.butjet.butJet3.dto.accrec.AccRecDtoUpdate;

import java.util.List;

public interface AccRecService {
   void deleteById(Long id,Long idAcc,Long idPerson);
   AccRecDtoResponse getById(Long id, Long idAcc, Long idPerson);
   List<AccRecDtoResponse> getAll(Long idAcc, Long idPerson);
   AccRecDtoResponse addAccRec(AccRecDtoAdd accRecDto, Long idAcc, Long idPerson);
   AccRecDtoResponse updateAccRec(AccRecDtoUpdate accRecDTO, Long id, Long idAcc, Long idPerson);
}
