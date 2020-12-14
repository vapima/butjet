package ru.vapima.butjet.butJet3.controllers.app;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vapima.butjet.butJet3.dto.acc.AccDtoAdd;
import ru.vapima.butjet.butJet3.dto.acc.AccDtoUpdate;
import ru.vapima.butjet.butJet3.dto.accrec.AccRecDtoAdd;
import ru.vapima.butjet.butJet3.model.Person;

import javax.validation.Valid;

public interface AccAppController {
    @GetMapping
    String getAccs(@RequestParam(value = "edit", required = false) Long idAccEdit, @RequestParam(value = "refresh", required = false) Long idAccRefresh, Model model, @AuthenticationPrincipal Person person, AccDtoAdd accDtoAdd);

    @PostMapping
    String newAcc(@Valid AccDtoAdd accDtoAdd, BindingResult bindingResult, Model model, @AuthenticationPrincipal Person person);

    @DeleteMapping("/{id}")
    String deleteAcc(Model model, @PathVariable("id") Long id, @AuthenticationPrincipal Person person);

    @PatchMapping("/{id}")
    String update(@Valid AccDtoUpdate accDTO, BindingResult bindingResult, Model model, @PathVariable("id") Long id, @AuthenticationPrincipal Person person);

    @PatchMapping("/accrec/{id}")
    String addNewAccrec(@Valid AccRecDtoAdd accRecDtoAdd, BindingResult bindingResult, Model model, @PathVariable("id") Long id, @AuthenticationPrincipal Person person);
}
