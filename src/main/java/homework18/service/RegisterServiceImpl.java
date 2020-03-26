package homework18.service;

import homework18.dto.RegisterDto;
import homework18.model.Role;
import homework18.model.User;
import homework18.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(RegisterDto form) {
        User user = User.builder()
                .email(form.getEmail())
                .hash(passwordEncoder.encode(form.getPassword()))
                .name(form.getName())
                .role(Role.USER)
                .build();

        userRepository.save(user);
    }
}

