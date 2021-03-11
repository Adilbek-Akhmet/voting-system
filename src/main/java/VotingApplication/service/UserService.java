package VotingApplication.service;

import VotingApplication.model.User;
import VotingApplication.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with such email does not found"));
    }

    public void signUp(User user) {
        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();

        if (userExists) {
            throw new IllegalStateException("Email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
    }

    @Transactional
    public void updateUser(User user, User updatedUser) {
        boolean userExists = userRepository.findByEmail(updatedUser.getEmail()).isPresent();

        if (user.getEmail().equals(updatedUser.getEmail())) {
            user.setEmail(updatedUser.getEmail());
        }
        else if (userExists) {
            throw new IllegalStateException("Email already taken");
        } else {
            user.setEmail(updatedUser.getEmail());
        }

        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setGroupName(updatedUser.getGroupName());
        user.setAge(updatedUser.getAge());
        user.setInterests(updatedUser.getInterests());
        user.setPassword(bCryptPasswordEncoder.encode(updatedUser.getPassword()));



    }


}
