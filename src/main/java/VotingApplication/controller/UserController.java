package VotingApplication.controller;

import VotingApplication.dto.RegistrationRequest;
import VotingApplication.model.User;
import VotingApplication.repository.UserRepository;
import VotingApplication.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/voting-system/users")
public class UserController {

    private final UserRepository userRepository;
    private final RegistrationService registrationService;

    @GetMapping
    @PreAuthorize("hasAuthority('USER_READ')")
    public String getAllUsers(Model model) {
        model.addAttribute("userList", userRepository.findAll());
        return "users";
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_READ')")
    public String getUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userRepository.getOne((long) id));
        return "profile";
    }

    @GetMapping("/registration")
    @PreAuthorize("hasAuthority('USER_ADD')")
    public String getRegistrationPage(Model model) {
        model.addAttribute("request", new RegistrationRequest());
        return "addUser";
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize("hasAuthority('USER_EDIT')")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userRepository.getOne(id));
        return "editUser";
    }

    @PostMapping("/{id}/edit")
    public String editUser(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userRepository.save(user);
        return "redirect:/voting-system/users/" + user.getId();
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
