package ru.vapima.butjet.butJet3.controllers.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vapima.butjet.butJet3.dto.person.PersonDtoAdd;
import ru.vapima.butjet.butJet3.dto.rdn.RdnDtoResponse;
import ru.vapima.butjet.butJet3.model.Person;
import ru.vapima.butjet.butJet3.service.AccService;
import ru.vapima.butjet.butJet3.service.PlanService;
import ru.vapima.butjet.butJet3.service.RdnService;

@Controller
public class HelloAppController {
    @Autowired
    private RdnService rdnService;
    @Autowired
    private AccService accService;
    @Autowired
    private PlanService planService;

    @GetMapping("/app/hello")
    public String getHello(Model model, @AuthenticationPrincipal Person person){
        RdnDtoResponse rdnDtoResponse=rdnService.calculateRdn(person.getId());
        model.addAttribute("rdn",rdnDtoResponse);
        model.addAttribute("accs",accService.getAll(person.getId()));
        model.addAttribute("plans",planService.getAll(person.getId()));
        return "hello";
    }
}
