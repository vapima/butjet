package ru.vapima.butjet.butJet3.controllers.app.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vapima.butjet.butJet3.controllers.app.PlanAppController;
import ru.vapima.butjet.butJet3.dto.plan.PlanDtoAdd;
import ru.vapima.butjet.butJet3.dto.plan.PlanDtoResponse;
import ru.vapima.butjet.butJet3.dto.plan.PlanDtoUpdate;
import ru.vapima.butjet.butJet3.model.Person;
import ru.vapima.butjet.butJet3.service.PlanService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/app/plans")
public class PlanAppControllerImpl implements PlanAppController {
    @Autowired
    private PlanService planService;

    @Override
    @GetMapping
    public String getPlans(@RequestParam(value = "edit", required = false) Long idPlanEdit, Model model, @AuthenticationPrincipal Person person, PlanDtoAdd planDTO) {
        if (person==null){ throw new IllegalArgumentException("Person is null");}
        List<PlanDtoResponse> plans=planService.getAll(person.getId());
        model.addAttribute("edit",idPlanEdit);
        model.addAttribute("plans", plans);
        model.addAttribute("planDtoAdd",planDTO);
        return "plan";
    }
    @Override
    @PostMapping
    public String newPlan(@Valid PlanDtoAdd planDTO, BindingResult bindingResult, Model model, @AuthenticationPrincipal Person person){
        if (bindingResult.hasErrors()) {
            List<PlanDtoResponse> plans=planService.getAll(person.getId());
            model.addAttribute("plans", plans);
            return "plan";
        }
        planService.addPlan(planDTO,person.getId());
        List<PlanDtoResponse> plans=planService.getAll(person.getId());
        model.addAttribute("plans", plans);
        return "plan";
    }
    @Override
    @DeleteMapping("/{id}")
    public String deletePlan(Model model, @PathVariable("id") Long id, @AuthenticationPrincipal Person person){
        planService.deleteById(id,person.getId());
        List<PlanDtoResponse> plans=planService.getAll(person.getId());
        model.addAttribute("plans", plans);
        return "plan";
    }

    @Override
    @PatchMapping("/{id}")
    public String update(@Valid PlanDtoUpdate planDTO, BindingResult bindingResult, Model model, @PathVariable("id") Long id, @AuthenticationPrincipal Person person) {
        if (bindingResult.hasErrors()) {
            List<PlanDtoResponse> plans = planService.getAll(person.getId());
            model.addAttribute("plans", plans);
            model.addAttribute("edit", id);
            model.addAttribute("regError", bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toArray(String[]::new));
            return "plan";
        }
        planService.updatePlan(planDTO,id,person.getId());
        List<PlanDtoResponse> plans=planService.getAll(person.getId());
        model.addAttribute("plans", plans);
        return "plan";
    }
}
