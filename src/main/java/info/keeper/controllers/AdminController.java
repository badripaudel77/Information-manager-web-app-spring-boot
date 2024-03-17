package info.keeper.controllers;

import info.keeper.models.AdminMessage;
import info.keeper.models.User;
import info.keeper.repositories.UserRepository;
import info.keeper.service.AdminService;
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
    private AdminService adminService;
    private UserRepository userRepository;

   @Autowired
    public AdminController(AdminService adminService, UserRepository userRepository) {
        this.adminService = adminService;
        this.userRepository = userRepository;
    }

    @GetMapping("/index")
    public String showAdminPage(Principal principal, Model model) {
        // In Prod app, put all the business login inside of the service and just use that in the controller.
        User user = userRepository.findUserByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("adminMessage", new AdminMessage());
        model.addAttribute("title", "Admin Page - " + principal.getName());
        return "admin/index";
    }

    @PostMapping("/saveMessage")
    public String saveMessage(@ModelAttribute("adminMessage")AdminMessage adminMessage,
                              Principal principal, HttpSession session) {
        this.adminService.saveAdminMessage(adminMessage, principal);
        session.setAttribute("message", new Message("You have sent notice to everyone", "alert-success"));
        return  "redirect:/admin/index/";
    }
}
