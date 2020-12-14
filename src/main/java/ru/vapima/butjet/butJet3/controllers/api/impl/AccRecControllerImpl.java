package ru.vapima.butjet.butJet3.controllers.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.vapima.butjet.butJet3.controllers.api.AccRecController;
import ru.vapima.butjet.butJet3.dto.accrec.AccRecDtoAdd;
import ru.vapima.butjet.butJet3.dto.accrec.AccRecDtoResponse;
import ru.vapima.butjet.butJet3.dto.accrec.AccRecDtoUpdate;
import ru.vapima.butjet.butJet3.service.AccRecService;

import java.util.List;

@RequestMapping("/api/persons/{id_person}/accounts/{id_acc}/accountrecords")
@RestController
public class AccRecControllerImpl implements AccRecController {

    @Autowired
    private AccRecService accRecService;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccRecDtoResponse save(@RequestBody AccRecDtoAdd accRecDTO, @PathVariable("id_acc") Long idAcc, @PathVariable("id_person") Long idPerson) {
       return accRecService.addAccRec(accRecDTO,idAcc,idPerson);
    }

    @Override
    @GetMapping("/{id}")
    public AccRecDtoResponse findById(@PathVariable("id") Long id, @PathVariable("id_acc") Long idAcc, @PathVariable("id_person") Long idPerson) {
        return accRecService.getById(id,idAcc,idPerson);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id,@PathVariable("id_acc") Long idAcc,@PathVariable("id_person") Long idPerson) {
        accRecService.deleteById(id,idAcc,idPerson);
    }

    @Override
    @GetMapping
    public List<AccRecDtoResponse> list(@PathVariable("id_acc") Long idAcc, @PathVariable("id_person") Long idPerson) {
        return accRecService.getAll(idAcc,idPerson);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public AccRecDtoResponse update(@RequestBody AccRecDtoUpdate accRecDTO, @PathVariable("id") Long id, @PathVariable("id_acc") Long idAcc, @PathVariable("id_person") Long idPerson) {
      return accRecService.updateAccRec(accRecDTO,id,idAcc,idPerson);
    }
}
