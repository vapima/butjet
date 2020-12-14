package ru.vapima.butjet.butJet3.controllers.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vapima.butjet.butJet3.dto.person.PersonDtoAdd;
import ru.vapima.butjet.butJet3.model.Person;
import ru.vapima.butjet.butJet3.service.PersonService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private PersonService personService;


    @GetMapping("/app/registration")
    public String registration(PersonDtoAdd personDtoAdd,Model model) {
        model.addAttribute("personDtoAdd",personDtoAdd);
        return "registration";
    }

    @PostMapping("/app/registration")
    public String addUser(@Valid PersonDtoAdd personForm, BindingResult bindingResult, Model model,HttpServletRequest httpServletRequest) {
        if (!personForm.getHashPassword().equals(personForm.getPasswordConfirm())){
            bindingResult.rejectValue("hashPassword","hashPassword","Password not equls");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("regError", bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toArray(String[]::new));
            return "registration";
        }

        try {personService.addPerson(personForm);}
        catch (IllegalArgumentException e){
            model.addAttribute("regError", "User already exist");
            return "registration";}
        try {
            httpServletRequest.login(personForm.getMail(),personForm.getHashPassword());
        } catch(ServletException ex) {
            return "redirect:/";
        }
        return "redirect:/";
    }

}
