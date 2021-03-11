package VotingApplication.dto;

import lombok.*;

@Data
public class RegistrationRequest {

    private String firstName;
    private String lastName;
    private String password;
    private String groupName;
    private Integer age;
    private String interests;
    private String email;

}
