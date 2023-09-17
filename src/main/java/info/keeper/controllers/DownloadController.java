package info.keeper.controllers;

import info.keeper.models.User;
import info.keeper.repositories.UserRepository;
import info.keeper.service.DownloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping(value = "/download", method = RequestMethod.GET)
public class DownloadController {
    @Autowired
    private DownloadService downloadService;

    @Autowired
    private UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger(DownloadController.class);

    // Use construction injection to field injection.
    public DownloadController(DownloadService downloadService, UserRepository userRepository) {
        this.downloadService = downloadService;
        this.userRepository = userRepository;
    }

    @GetMapping("/notes")
    public String downloadNotesByUser(Model model, Principal principal) {
        Integer userId = userRepository.findUserByUsername(principal.getName()).getId();
        boolean isNotesDownloaded = downloadService.downloadUsersNotes(userId);
        String message = null;
        if(isNotesDownloaded) message = "File has been downloaded at location " +
                System.getProperty("user.home") + "/notes";
        String username = principal.getName();
        //get user using username
        User user = userRepository.findUserByUsername(username);

        model.addAttribute("title", "User Dashboard Page - Information Keeper");

        model.addAttribute("user", user);
        model.addAttribute("downloadStatus", message);
        logger.info("Notes getting downloaded for the user with ID :" + userId );
        return "normal_user/user_dashboard";
    }
}
