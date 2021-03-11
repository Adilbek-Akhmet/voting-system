package VotingApplication.controller;

import VotingApplication.dto.RegistrationRequest;
import VotingApplication.model.User;
import VotingApplication.repository.UserRepository;
import VotingApplication.service.LoginService;
import VotingApplication.service.RegistrationService;
import VotingApplication.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/voting-system/users")
public class UserController {

    private final UserRepository userRepository;
    private final LoginService loginService;
    private final UserService userService;
    private final RegistrationService registrationService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String getAllUsers(Model model) {
        model.addAttribute("userList", userRepository.findAll());
        return "users";
    }

    @GetMapping("/profile")
    @PreAuthorize("hasAuthority('USER_READ')")
    public String getUser(Model model) {
        model.addAttribute("user", loginService.loggedUser());
        return "profile";
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('USER_ADD')")
    public String addUser(Model model) {
        model.addAttribute("request", new RegistrationRequest());
        return "addUser";
    }

    @PostMapping("/add")
    public String addNewUser(@ModelAttribute("request") RegistrationRequest request) {
        registrationService.register(request);
        return "redirect:/voting-system/users";
    }

    @GetMapping("/{id}/update")
    @PreAuthorize("hasAuthority('USER_UPDATE')")
    public String updateUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userRepository.getOne(id));
        return "updateUser";
    }

    @PostMapping("/{id}/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userRepository.save(user);
        return "redirect:/voting-system/users/";
    }

    @GetMapping("/edit")
    @PreAuthorize("hasAuthority('USER_EDIT')")
    public String editUser(Model model) {
        User user = loginService.loggedUser();
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") User user) {
        userService.updateUser(loginService.loggedUser(), user);
        return "redirect:/voting-system/users/profile";
    }

    @GetMapping("/{id}/delete")
    @PreAuthorize("hasAuthority('USER_DELETE')")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userRepository.getOne(id));
        return "deleteUser";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userRepository.delete(user);
        return "redirect:/voting-system/users/";
    }








}
