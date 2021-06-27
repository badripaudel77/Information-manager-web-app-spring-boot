package info.keeper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public boolean sendEmail(String to,String from, String subject, int code) {
        String name = to.split("@")[0].toUpperCase();
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(mailMessage, true);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(subject);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);

            mailMessage.setContent("<html>" + "<head>" + "<h1>Hello&nbsp;" + name + "</h1>" + "</head>" + "<body>"
                    + "<p>" + "Please enter this code to reset password " + "</p>"
                    + "<h3>" + code + "</h3>" + "<p>" + "With Regards" + "<br>"
                    + "Info Keeper Team" + "</p>" + "</body>" + "</html>", "text/html");
            javaMailSender.send(mailMessage);
            return true;
        }
        catch (MessagingException e) {
            return false;
        }
    }
}
