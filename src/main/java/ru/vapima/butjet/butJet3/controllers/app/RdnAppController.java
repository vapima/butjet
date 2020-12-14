package ru.vapima.butjet.butJet3.controllers.app;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vapima.butjet.butJet3.model.Person;

public interface RdnAppController {
    @GetMapping
    String getRdn(Model model, @AuthenticationPrincipal Person person);
}
