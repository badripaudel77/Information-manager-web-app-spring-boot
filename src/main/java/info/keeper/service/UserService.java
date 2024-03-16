package info.keeper.service;

import info.keeper.models.AdminMessage;
import info.keeper.models.User;
import info.keeper.repositories.AdminRepository;
import info.keeper.repositories.UserRepository;
import info.keeper.utils.UserRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private JdbcTemplate jdbcTemplate;
    private EntityManager entityManager;

    public UserService(UserRepository userRepository,
                       JdbcTemplate jdbcTemplate, EntityManager entityManager) {
        this.userRepository = userRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.entityManager = entityManager;
    }

    public  User getUserByUsername(String username) {
      User user = userRepository.findUserByUsername(username);
      return user;
    }

    public ArrayList<AdminMessage> getAllAdminMessages() {
        String query = "SELECT * FROM admin_message";
        Query namedQuery = this.entityManager.createNativeQuery(query, AdminMessage.class);
        return (ArrayList<AdminMessage>)namedQuery.getResultList();
        // return getAllAdminMessagesUsingJdbcT(); // Same as above.
    }

    // Using JdbcTemplate
    public ArrayList<AdminMessage> getAllAdminMessagesUsingJdbcT() {
        String query = "SELECT * FROM admin_message";
        List<AdminMessage> adminMessageList = this.jdbcTemplate.query(query, new UserRowMapper());
        return (ArrayList<AdminMessage>) adminMessageList;
    }
}

