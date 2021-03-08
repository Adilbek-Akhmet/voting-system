package VotingApplication.service;

import VotingApplication.dto.RegistrationRequest;
import VotingApplication.model.User;
import VotingApplication.model.enums.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;

    public void register(RegistrationRequest request) {
        userService.signUp(
                new User(
                        request.getEmail(),
                        request.getPassword(),
                        Role.USER,
                        request.getFirstName(),
                        request.getLastName(),
                        request.getGroupName(),
                        request.getAge(),
                        request.getInterests()
                )
        );


    }
}
