package info.keeper.service;

import info.keeper.models.AdminMessage;
import info.keeper.repositories.AdminRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;

@Service
public class AdminService {

    private AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public void saveAdminMessage(AdminMessage adminMessage, Principal principal) {
        adminMessage.setDate(new Date());
        adminMessage.setPostedBy(principal.getName().split("@")[0]); // show only before @
        adminRepository.save(adminMessage);
    }
}
