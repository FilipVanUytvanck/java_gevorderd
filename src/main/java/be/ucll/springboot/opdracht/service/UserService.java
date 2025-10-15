package be.ucll.springboot.opdracht.service;

import be.ucll.springboot.opdracht.model.User;
import be.ucll.springboot.opdracht.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(String email, String firstName, String lastName, String password) {
        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        // Store a strong, salted hash instead of plaintext
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
