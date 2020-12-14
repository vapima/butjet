package ru.vapima.butjet.butJet3.service;

import ru.vapima.butjet.butJet3.dto.acc.AccDtoAdd;
import ru.vapima.butjet.butJet3.dto.acc.AccDtoResponse;
import ru.vapima.butjet.butJet3.dto.acc.AccDtoUpdate;

import java.util.List;

public interface AccService {
   void deleteById(Long id,Long idPerson);
   AccDtoResponse getById(Long id, Long idPerson);
   List<AccDtoResponse> getAll(Long idPerson);
   AccDtoResponse addAcc(AccDtoAdd accDto, Long idPerson);
   AccDtoResponse updateAcc(AccDtoUpdate accDTO, Long id, Long idPerson);
}
