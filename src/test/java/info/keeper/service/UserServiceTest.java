package info.keeper.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import info.keeper.models.User;
import info.keeper.repositories.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * REF: https://www.baeldung.com/java-spring-mockito-mock-mockbean (Mockito.Mock vs @Mock syntax)
 * REF : https://stackoverflow.com/questions/16467685/difference-between-mock-and-injectmocks (@InjectMocks and @Mock)
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    // This UserRepository will be mocked (for some method of it, we will be returning the result of the mock and not the real db call)
    @Mock
    private UserRepository userRepository;

    /**
     * Mark a field on which injection should be performed.
     * @InjectMocks creates the instance of the class and injects the mocks that are
     * created with the @Mock (or @Spy) annotations into this instance.
     * REF: https://stackoverflow.com/questions/16467685/difference-between-mock-and-injectmocks
     */
    @InjectMocks
    private UserService userService; // Service under test, all services ( or repo ..) with Mock annotations applied will be injected into it.

    // Define fields.
    private static User user;
    private static String username;

    @BeforeAll
    static void setUpData() {
        username = "badripaudel77@gmail.com";
        user = new User();
        user.setEmail(username);
    }

    @BeforeEach
    void setUp() {
        // Any setup before each test.
    }
    @AfterEach
    void cleanUp() {
        // Any cleanup after each test.
    }

    @Test
    @DisplayName("Test that getUserByUsername() of the service returns correct user.")
    void testGetUserByUsername() {
        /**
         * Define mock behavior for userRepository
         * That is, when user userRepository.findUserByUsername() is called, return the user (mock it, instead of calling the db)
         */
        when(userRepository.findUserByUsername(username)).thenReturn(user);
        // Mockito.when(userRepository.findUserByUsername(username)).thenReturn(user);
        // Call the method under test
        User userByUsername = userService.getUserByUsername(username);

        // Verify that the userRepository method was called with the correct parameter
        verify(userRepository).findUserByUsername("badripaudel77@gmail.com");

        // Verify that the returned user is the same as the expected user
        assertThat(userByUsername).isEqualTo(user);
        assert userByUsername.getEmail().equals(user.getEmail()) == true;
    }

    @AfterAll
    static void tearDown() {
        // Any cleanup after all the tests are executed.
    }
}
