package art.dev.backend.services;


import art.dev.backend.domains.User;
import art.dev.backend.exceptions.UserConfirmPasswordException;
import art.dev.backend.exceptions.UsernameAlreadyExistsException;
import art.dev.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    public User saveUser(User newUser) {

        try {
            String password = newUser.getPassword();
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            newUser.setUsername(newUser.getUsername());
            if (password.equals(newUser.getConfirmPassword())){
                newUser.setConfirmPassword(bCryptPasswordEncoder.encode(newUser.getConfirmPassword()));
            } else {
                throw new UserConfirmPasswordException("Confirm password");
            }
            newUser = userRepository.save(newUser);
            return newUser;
        } catch (DataIntegrityViolationException e) {
            throw new UsernameAlreadyExistsException("Username " + newUser.getUsername() + " already exists");
        }

    }


}