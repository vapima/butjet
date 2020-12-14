package ru.vapima.butjet.butJet3.controllers.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.vapima.butjet.butJet3.controllers.api.AccController;
import ru.vapima.butjet.butJet3.dto.acc.AccDtoAdd;
import ru.vapima.butjet.butJet3.dto.acc.AccDtoResponse;
import ru.vapima.butjet.butJet3.dto.acc.AccDtoUpdate;
import ru.vapima.butjet.butJet3.service.AccService;

import java.util.List;

@RequestMapping("/api/persons/{id_person}/accounts")
@RestController
public class AccControllerImpl implements AccController {

    @Autowired
    private AccService accService;


    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccDtoResponse save(@RequestBody AccDtoAdd accDTO, @PathVariable("id_person") Long idPerson) {
        return accService.addAcc(accDTO,idPerson);
    }

    @Override
    @GetMapping("/{id}")
    public AccDtoResponse findById(@PathVariable("id") Long id, @PathVariable("id_person") Long idPerson) {
        return accService.getById(id,idPerson);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id,@PathVariable("id_person") Long idPerson) {
        accService.deleteById(id,idPerson);
    }

    @Override
    @GetMapping
    public List<AccDtoResponse> list(@PathVariable("id_person") Long idPerson) {
        return accService.getAll(idPerson);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public AccDtoResponse update(@RequestBody AccDtoUpdate accDTO, @PathVariable("id") Long id, @PathVariable("id_person") Long idPerson) {
        return accService.updateAcc(accDTO,id,idPerson);
    }
}
