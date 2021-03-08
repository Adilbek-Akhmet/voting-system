package VotingApplication.controller;

import VotingApplication.dto.RegistrationRequest;
import VotingApplication.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "voting-system/authentication")
public class AuthenticationController {

    private final RegistrationService registrationService;

    @Autowired
    public AuthenticationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping( "/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("request", new RegistrationRequest());
        return "registration";
    }

    @PostMapping("/registration")
    public String addNewUser(@ModelAttribute("request") RegistrationRequest request) {
        registrationService.register(request);
        return "redirect:/voting-system/authentication/login";
    }


}
