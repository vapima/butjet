package ru.vapima.butjet.butJet3.controllers.app.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vapima.butjet.butJet3.controllers.app.AccAppController;
import ru.vapima.butjet.butJet3.dto.acc.AccDtoAdd;
import ru.vapima.butjet.butJet3.dto.acc.AccDtoResponse;
import ru.vapima.butjet.butJet3.dto.acc.AccDtoUpdate;
import ru.vapima.butjet.butJet3.dto.accrec.AccRecDtoAdd;
import ru.vapima.butjet.butJet3.model.Person;
import ru.vapima.butjet.butJet3.service.AccRecService;
import ru.vapima.butjet.butJet3.service.AccService;

import javax.validation.Valid;
import java.util.List;

@Controller
//TODO MORE REST rename acc to accountS
@RequestMapping("/app/accounts")
public class AccAppControllerImpl implements AccAppController {
    @Autowired
    private AccService accService;
    @Autowired
    private AccRecService accRecService;
    @Override
    @GetMapping
    public String getAccs(@RequestParam(value = "edit", required = false) Long idAccEdit, @RequestParam(value = "refresh", required = false) Long idAccRefresh, Model model, @AuthenticationPrincipal Person person, AccDtoAdd accDtoAdd) {
        if (person==null){ throw new IllegalArgumentException("Person is null");}
        List<AccDtoResponse> accounts=accService.getAll(person.getId());
        model.addAttribute("edit",idAccEdit);
        model.addAttribute("refresh",idAccRefresh);
        model.addAttribute("accounts", accounts);
        model.addAttribute("accDtoAdd",accDtoAdd);
        return "acc";
    }
    @Override
    @PostMapping
    public String newAcc(@Valid AccDtoAdd accDtoAdd, BindingResult bindingResult, Model model, @AuthenticationPrincipal Person person){
        if (bindingResult.hasErrors()) {
            List<AccDtoResponse> accounts=accService.getAll(person.getId());
            model.addAttribute("accounts", accounts);
            return "acc";
        }
        accService.addAcc(accDtoAdd,person.getId());

        List<AccDtoResponse> accounts=accService.getAll(person.getId());
        model.addAttribute("accounts", accounts);
        return "acc";
    }

    @Override
    @DeleteMapping("/{id}")
    public String deleteAcc(Model model, @PathVariable("id") Long id, @AuthenticationPrincipal Person person){
        accService.deleteById(id,person.getId());
        List<AccDtoResponse> accounts=accService.getAll(person.getId());
        model.addAttribute("accounts", accounts);
        return "acc";
    }

    @Override
    @PatchMapping("/{id}")
    public String update(@Valid AccDtoUpdate accDTO, BindingResult bindingResult, Model model, @PathVariable("id") Long id, @AuthenticationPrincipal Person person) {
        if (bindingResult.hasErrors()) {
            List<AccDtoResponse> accounts = accService.getAll(person.getId());
            model.addAttribute("accounts", accounts);
            model.addAttribute("regError", bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toArray(String[]::new));
            return "acc";
        }
        accService.updateAcc(accDTO,id,person.getId());
        List<AccDtoResponse> accounts=accService.getAll(person.getId());
        model.addAttribute("accounts", accounts);
        return "acc";
    }

    //Account Records Controller
    @Override
    @PostMapping("/{idAcc}/accountrecords")
    public String addNewAccrec(@Valid AccRecDtoAdd accRecDtoAdd, BindingResult bindingResult, Model model, @PathVariable("idAcc") Long idAcc, @AuthenticationPrincipal Person person) {
        if (bindingResult.hasErrors()) {
            List<AccDtoResponse> accounts = accService.getAll(person.getId());
            model.addAttribute("accounts", accounts);
            model.addAttribute("regError", bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toArray(String[]::new));
            return "acc";
        }
        accRecService.addAccRec(accRecDtoAdd,idAcc,person.getId());
        List<AccDtoResponse> accounts=accService.getAll(person.getId());
        model.addAttribute("accounts", accounts);
        return "acc";
    }
    
    
    
}
