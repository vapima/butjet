package ru.vapima.butjet.butJet3.controllers.app;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vapima.butjet.butJet3.dto.plan.PlanDtoAdd;
import ru.vapima.butjet.butJet3.dto.plan.PlanDtoUpdate;
import ru.vapima.butjet.butJet3.model.Person;

import javax.validation.Valid;

public interface PlanAppController {
    @GetMapping
    String getPlans(@RequestParam(value = "edit", required = false) Long idPlanEdit, Model model, @AuthenticationPrincipal Person person, PlanDtoAdd planDTO);

    @PostMapping
    String newPlan(@Valid PlanDtoAdd planDTO, BindingResult bindingResult, Model model, @AuthenticationPrincipal Person person);

    @DeleteMapping("/{id}")
    String deletePlan(Model model, @PathVariable("id") Long id, @AuthenticationPrincipal Person person);

    @PatchMapping("/{id}")
    String update(@Valid PlanDtoUpdate planDTO, BindingResult bindingResult, Model model, @PathVariable("id") Long id, @AuthenticationPrincipal Person person);
}
