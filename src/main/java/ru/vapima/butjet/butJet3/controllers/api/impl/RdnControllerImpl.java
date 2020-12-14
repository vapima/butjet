package ru.vapima.butjet.butJet3.controllers.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.vapima.butjet.butJet3.controllers.api.PlanController;
import ru.vapima.butjet.butJet3.controllers.api.RdnController;
import ru.vapima.butjet.butJet3.dto.plan.PlanDtoAdd;
import ru.vapima.butjet.butJet3.dto.plan.PlanDtoResponse;
import ru.vapima.butjet.butJet3.dto.plan.PlanDtoUpdate;
import ru.vapima.butjet.butJet3.dto.rdn.RdnDtoResponse;
import ru.vapima.butjet.butJet3.service.PersonService;
import ru.vapima.butjet.butJet3.service.PlanService;
import ru.vapima.butjet.butJet3.service.RdnService;

import java.util.List;

@RequestMapping("/api/persons/{id_person}/rdn")
@RestController
public class RdnControllerImpl implements RdnController {

    @Autowired
    private RdnService rdnService;

    @GetMapping
    @Override
    public RdnDtoResponse get(@PathVariable("id_person") Long idPerson) {
        return rdnService.calculateRdn(idPerson);
    }
}
