package info.keeper.controllers;

import info.keeper.models.AdminMessage;
import info.keeper.models.User;
import info.keeper.repositories.AdminRepository;
import info.keeper.repositories.UserRepository;
import info.keeper.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Date;

@Controller
@RequestMapping(value = "/admin", method = RequestMethod.GET)
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/index")
    public String showAdminPage(Principal principal, Model model) {
        User user = userRepository.findUserByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("adminMessage", new AdminMessage());
        model.addAttribute("title", "Admin Page - " + principal.getName());
        return "admin/index";
    }

    @PostMapping("/saveMessage")
    public String saveMessage(@ModelAttribute("adminMessage")AdminMessage adminMessage,
                              Principal principal, HttpSession session) {
        adminMessage.setDate(new Date());
        adminMessage.setPostedBy(principal.getName().split("@")[0]); // show only before @
        adminRepository.save(adminMessage);
        session.setAttribute("message", new Message("You have sent notice to everyone", "alert-success"));
        return  "redirect:/admin/index/";
    }
}
