package ru.vapima.butjet.butJet3.service;

import ru.vapima.butjet.butJet3.dto.accrec.AccRecDtoAdd;
import ru.vapima.butjet.butJet3.dto.accrec.AccRecDtoResponse;
import ru.vapima.butjet.butJet3.dto.accrec.AccRecDtoUpdate;
import ru.vapima.butjet.butJet3.dto.rdn.RdnDtoResponse;
import ru.vapima.butjet.butJet3.model.Person;

import java.util.List;

public interface RdnService {
   RdnDtoResponse calculateRdn (Long idPerson);
}
