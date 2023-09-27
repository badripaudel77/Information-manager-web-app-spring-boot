package info.keeper.controllers;

import info.keeper.models.User;
import info.keeper.repositories.UserRepository;
import info.keeper.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

@Controller
// handling all routes related to home page [ public page ]
public class HomeController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // displays home page
    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("title", "Home Page - Information Keeper");

        return "home"; // return home.html page from templates folder
    }

    // displays custom login page @Handle custom login
    @GetMapping("/loginUser")
    public String loginPage(Model model) {
        model.addAttribute("title", "Login Page - Information Keeper");
        User user = new User();
        model.addAttribute("user", user);
        return "login"; // return home.html page from templates folder
    }

    // displays register page
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("title", "Register Page - Information Keeper");
        return "register"; // return home.html page from templates folder
    }

    //register user
    @PostMapping("/registerUser")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model,
                               HttpSession session, @RequestParam(value = "terms", defaultValue = "false") boolean terms)  {
        try {
            if(!terms) throw new Exception("Please agree to the terms and condition first."); // will go to catch block
            if(result.hasErrors()) {
                model.addAttribute("user", user);
                // System.out.println("error is " + result.getAllErrors().toString());
                return "register";
            }
            if(validateUser(user)) {
                throw  new Exception("User with that email  already exists, try again with your new email.");
            }
            //user.setRole("ROLE_ADMIN"); // saving few data for admin
            user.setRole("ROLE_USER"); // DEFAULT Role is USER
            user.setActive(true);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setEmail(user.getEmail().toLowerCase());
            this.userRepository.save(user);
            model.addAttribute("user", new User());
            session.setAttribute("message",  new Message("Registration has been completed, now sign in" , "alert-success"));
        }
        catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("message",  new Message("Something Went Wrong " + e.getMessage(), "alert-danger"));
            model.addAttribute("user", user);
            return "register";
        }
        return "register";
    }

    //show password reset page
    @GetMapping("/forgot-password")
    public String showForgotPWPage(Model model) {
        model.addAttribute("title", "Password Reset Page");
        return "normal_user/forgot-password";
    }

    //validate user
    private boolean validateUser(User user) {
         User u =  userRepository.findUserByUsername(user.getEmail());
         return u!= null;
    }
}
