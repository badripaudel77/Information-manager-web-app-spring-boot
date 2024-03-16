package info.keeper.controllers;

import info.keeper.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * REF: https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/autoconfigure/web/servlet/WebMvcTest.html
 * @SpringBootTest loads whole application context like MVC, service, repository layer etc.
 * If you are looking to load your full application configuration and use MockMVC, you should consider @SpringBootTest combined with @AutoConfigureMockMvc rather than this annotation.
 * By default, tests annotated with @WebMvcTest will also auto-configure Spring Security and MockMvc (include support for HtmlUnit WebClient and Selenium WebDriver). For more fine-grained control of MockMVC the @AutoConfigureMockMvc annotation can be used.
 */
//@SpringBootTest
@WebMvcTest(HomeController.class)
class HomeControllerTest {

    @Autowired
    HomeController homeController;
    @MockBean
    UserRepository userRepository;
    @Autowired
    MockMvc mockMvc;

    private String homepageTitle;
    private String loginpageTitle;
    private String registerpageTitle;

    @BeforeEach
    void setUp() {
         homepageTitle = "<title>Home Page - Information Keeper</title>";
         loginpageTitle = "Login Page - Information Keeper";
         registerpageTitle = "Register Page - Information Keeper";
    }

    @AfterEach
    void tearDown() {
        // Write some cleanup process.
        // This is just for demo.
        homepageTitle = loginpageTitle = registerpageTitle = "";
        assert homepageTitle.equals(loginpageTitle) && loginpageTitle.equals(registerpageTitle);
    }

    /**
     * Use Assertion library for better assertion
     * When user navigates to /, method GET, make sure controller is not null and title is as expected
     * and is rendering the correct index page.
     * More the coverage, the better it will be.
     */
    @Test
    @DisplayName("Ensure that GET: / returns the expected response with status and title of the page")
    void testHomePage() throws Exception {
        assertThat(homeController).isNotNull();
        // assert homeController != null; // => same as above
        this.mockMvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(content().string(containsString(homepageTitle)));
    }

    @Test
    void testLoginPage() {

    }

    @Test
    void testRegisterPage() {
    }
}