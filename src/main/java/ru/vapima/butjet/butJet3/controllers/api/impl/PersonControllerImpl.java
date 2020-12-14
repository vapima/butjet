package ru.vapima.butjet.butJet3.controllers.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.vapima.butjet.butJet3.controllers.api.PersonController;
import ru.vapima.butjet.butJet3.dto.person.PersonDtoAdd;
import ru.vapima.butjet.butJet3.dto.person.PersonDtoResponse;
import ru.vapima.butjet.butJet3.dto.person.PersonDtoUpdate;
import ru.vapima.butjet.butJet3.service.PersonService;

import java.util.List;
import java.util.stream.Collectors;


@RequestMapping("/api/persons")
@RestController
public class PersonControllerImpl implements PersonController {

    @Autowired
    private PersonService personService;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDtoResponse save( PersonDtoAdd personDTO) {
       return personService.addPerson(personDTO);
    }

    @Override
    @GetMapping("/{id}")
    public PersonDtoResponse findById(Long id) {
        return personService.getById(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(Long id) {
        personService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<PersonDtoResponse> list() {
        //String email=SecurityContextHolder.getContext().getAuthentication().getName();
        //List<PersonDtoResponse> list=personService.getAll();
        //return list.stream().filter(personDtoResponse -> personDtoResponse.getMail().equalsIgnoreCase(email)).collect(Collectors.toList());
        ///authentication.getName();
        return personService.getAll();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public PersonDtoResponse update(PersonDtoUpdate personDTO, Long id) {
      return personService.updatePerson(personDTO,id);
    }
}
