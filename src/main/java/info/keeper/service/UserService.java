package info.keeper.service;

import info.keeper.models.User;
import info.keeper.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public  User getUserByUsername(String username) {
      User user = userRepository.findUserByUsername(username);
      return user;
    }
}

