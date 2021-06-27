package info.keeper.controllers;

import info.keeper.models.User;
import info.keeper.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class SettingsController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/settings")
    public String showSettings(Principal principal, Model model) {
        User user = userRepository.findUserByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("title", "Settings Page - " + user.getName());
        return "normal_user/settings";
    }

    @PostMapping("/users/resetPassword")
    public String resetPassword(@RequestParam("oldPassword") String oldPassword,@RequestParam("newPassword") String newPassword,
                                Principal principal, Model model) {
        //TODO : reset password logic
        User user = userRepository.findUserByUsername(principal.getName());
        System.out.println("original password is " + user.getPassword() + "entered pw : " + oldPassword + " new pw " + newPassword);
        model.addAttribute("user", user);
        model.addAttribute("title", "Settings Page - " + user.getName());

        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        boolean isPasswordMatches = bcrypt.matches(oldPassword, user.getPassword());
        if(isPasswordMatches) {
            //update password
            System.out.println("password matched");
            //user.setPassword(newPassword);
            user.setPassword(bcrypt.encode(newPassword));
            userRepository.save(user);
            model.addAttribute("pwUpdated", "Password has been updated.");
        }
        else {
            System.out.println("password don't matches");
            model.addAttribute("pwMismatch", "Password didn't match, try again.");
        }
        return "normal_user/settings";
    }
}