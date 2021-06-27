package info.keeper.controllers;

import info.keeper.models.AdminMessage;
import info.keeper.models.Contact;
import info.keeper.models.User;
import info.keeper.repositories.AdminRepository;
import info.keeper.repositories.ContactRepository;
import info.keeper.repositories.UserRepository;
import info.keeper.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/users", method = RequestMethod.GET) // for user
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping
    public String dashboard(Model model, Principal principal) {
        String username = principal.getName();
        //get user using username
        User user = userRepository.findUserByUsername(username);

        model.addAttribute("title", "User Dashboard Page - Information Keeper");

        model.addAttribute("user", user);
        return "normal_user/user_dashboard";
    }

    //show profile page
    @GetMapping("/profile/")
    public String showProfileOfUser(Model model, Principal principal) {
        User user = userRepository.findUserByUsername(principal.getName());

        model.addAttribute("user", user);
        model.addAttribute("title", "Profile Page - " + user.getName());
        return "normal_user/profile";
    }

    // displays about page
    @GetMapping("/admin-notice")
    public String aboutPage(Model model) {
        model.addAttribute("title", "Admin Notice - Information Keeper");
        ArrayList<AdminMessage> adminMessages = adminRepository.findAll();
        System.out.println(adminMessages.size());

//        List<AdminMessage> adminMessages = new ArrayList<>();
//        adminMessages.add(new AdminMessage(1, "this is demo message1"));
//        adminMessages.add(new AdminMessage(2, "this is demo message2"));
//        adminMessages.add(new AdminMessage(3, "this is demo message3"));
        model.addAttribute("adminMsgList", adminMessages);
        return "normal_user/admin_notice"; // return home.html page from templates folder
    }
}
