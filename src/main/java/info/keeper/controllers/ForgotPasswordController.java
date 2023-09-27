package info.keeper.controllers;

import info.keeper.models.User;
import info.keeper.repositories.UserRepository;
import info.keeper.service.EmailService;
import info.keeper.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.concurrent.ThreadLocalRandom;

// handles : Password reset when user clicks on forgot password
@Controller
public class ForgotPasswordController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private  BCryptPasswordEncoder encoder;

    @Value("${spring.mail.username}")
    private String fromMail;

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam("username") String email, HttpSession session) {
        User user = userRepository.findUserByUsername(email);
        if(user== null) {
            session.setAttribute("message", new Message("This email doesn't exist.", "alert-danger"));
            return "normal_user/forgot-password";
        }
        else if(!user.getEmail().equals(email)) {
            session.setAttribute("message", new Message("This email doesn't exist.", "alert-danger"));
            return "normal_user/forgot-password";
        }
        else {
            //write logic to send email

            final boolean isSent = sendEmail(email, session);
            if(!isSent) {
                session.setAttribute("message", new Message("something went wrong while sending email.", "alert-info"));
                return "normal_user/forgot-password";
            }
            session.setAttribute("message", new Message("Verification code has been sent.", "alert-success"));
            session.setAttribute("email", email);
        }
        return "normal_user/reset_password";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam("") int code, @RequestParam("new_password") String newPassword, HttpSession session){
         String email = session.getAttribute("email").toString();
         int verificationCode = (int) session.getAttribute("verificationCode");
         if(!(code == verificationCode)) {
             session.setAttribute("message", new Message("Code doesn't match, try again.", "alert-danger"));
             return "normal_user/reset_password";
         }
         User user = userRepository.findUserByUsername(email);
         if(user== null || !user.getEmail().equals(email)) {
             session.setAttribute("message", new Message("User doesn't exist", "alert-danger"));
             return "normal_user/reset_password";
         }

         user.setPassword(encoder.encode(newPassword));
         userRepository.save(user);
         session.setAttribute("message", new Message("Password has been updated, login now", "alert-success"));
         return "normal_user/reset_password";
    }
    
    private boolean sendEmail(String email, HttpSession session) {
        int randomNumber = ThreadLocalRandom.current().nextInt(100000,999999);
        session.setAttribute("verificationCode", randomNumber); //set it to session
        final boolean isSent = emailService.sendEmail(email, fromMail, "Password Reset Code", randomNumber);
        return isSent;
    }
}
