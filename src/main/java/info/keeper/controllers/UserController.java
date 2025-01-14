package info.keeper.controllers;

import info.keeper.models.AdminMessage;
import info.keeper.models.User;
import info.keeper.repositories.AdminRepository;
import info.keeper.repositories.UserRepository;
import info.keeper.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/users", method = RequestMethod.GET) // for user
public class UserController {

    private UserRepository userRepository;
    private AdminRepository adminRepository;
    private UserService userService;

    public UserController(UserRepository userRepository, AdminRepository adminRepository, UserService userService) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.userService = userService;
    }

    @GetMapping
    public String dashboard(Model model, Principal principal) {
        //get user using username
        User user = userService.getUserByUsername(principal.getName());
        model.addAttribute("title", "User Dashboard Page - Information Keeper");
        model.addAttribute("user", user);
        return "normal_user/user_dashboard";
    }

    //show profile page
    @GetMapping("/profile/")
    public String showProfileOfUser(Model model, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());

        model.addAttribute("user", user);
        model.addAttribute("title", "Profile Page - " + user.getName());
        return "normal_user/profile";
    }

    // displays about page
    @GetMapping("/admin-notice")
    public String aboutPage(Model model) {
        model.addAttribute("title", "Admin Notice - Information Keeper");
        ArrayList<AdminMessage> adminMessages = userService.getAllAdminMessages();
        model.addAttribute("adminMsgList", adminMessages);
        return "normal_user/admin_notice"; // return home.html page from templates folder
    }
}
