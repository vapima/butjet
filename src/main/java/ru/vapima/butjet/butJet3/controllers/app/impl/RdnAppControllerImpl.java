package ru.vapima.butjet.butJet3.controllers.app.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vapima.butjet.butJet3.controllers.app.RdnAppController;
import ru.vapima.butjet.butJet3.dto.rdn.RdnDtoResponse;
import ru.vapima.butjet.butJet3.model.Person;
import ru.vapima.butjet.butJet3.service.RdnService;

@Controller
@RequestMapping("/app/rdn/calculate")
public class RdnAppControllerImpl implements RdnAppController {
    @Autowired
    private RdnService rdnService;

    @Override
    @GetMapping
    public String getRdn(Model model, @AuthenticationPrincipal Person person) {
        RdnDtoResponse rdnDtoResponse=rdnService.calculateRdn(person.getId());
        model.addAttribute("rdn",rdnDtoResponse);
        return "calculate";
    }

}
