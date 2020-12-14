package ru.vapima.butjet.butJet3.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.vapima.butjet.butJet3.dto.person.PersonDtoAdd;
import ru.vapima.butjet.butJet3.dto.person.PersonDtoResponse;
import ru.vapima.butjet.butJet3.dto.person.PersonDtoUpdate;

import java.util.List;

public interface PersonService extends UserDetailsService {
   void deleteById(Long id);
   PersonDtoResponse getById(Long id);
   List<PersonDtoResponse> getAll();
   PersonDtoResponse addPerson(PersonDtoAdd personDto);
   PersonDtoResponse updatePerson(PersonDtoUpdate personDTO, Long id);
}
