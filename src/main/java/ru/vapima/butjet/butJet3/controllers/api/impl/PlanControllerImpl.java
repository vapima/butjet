package ru.vapima.butjet.butJet3.controllers.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.vapima.butjet.butJet3.controllers.api.PlanController;
import ru.vapima.butjet.butJet3.dto.plan.PlanDtoAdd;
import ru.vapima.butjet.butJet3.dto.plan.PlanDtoResponse;
import ru.vapima.butjet.butJet3.dto.plan.PlanDtoUpdate;
import ru.vapima.butjet.butJet3.service.PlanService;

import java.util.List;

@RequestMapping("/api/persons/{id_person}/plans")
@RestController
public class PlanControllerImpl implements PlanController {

    @Autowired
    private PlanService planService;


    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlanDtoResponse save(@RequestBody PlanDtoAdd planDTO, @PathVariable("id_person") Long idPerson) {
       return planService.addPlan(planDTO,idPerson);
    }

    @Override
    @GetMapping("/{id}")
    public PlanDtoResponse findById(@PathVariable("id") Long id, @PathVariable("id_person") Long idPerson) {
        return planService.getById(id,idPerson);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id,@PathVariable("id_person") Long idPerson) {
        planService.deleteById(id,idPerson);
    }

    @Override
    @GetMapping
    public List<PlanDtoResponse> list(@PathVariable("id_person") Long idPerson) {
        return planService.getAll(idPerson);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public PlanDtoResponse update(@RequestBody PlanDtoUpdate planDTO, @PathVariable("id") Long id, @PathVariable("id_person") Long idPerson) {
      return planService.updatePlan(planDTO,id,idPerson);
    }
}
