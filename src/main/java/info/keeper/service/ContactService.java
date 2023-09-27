package info.keeper.service;

import info.keeper.models.Contact;
import info.keeper.models.User;
import info.keeper.repositories.ContactRepository;
import info.keeper.repositories.UserRepository;
import info.keeper.utils.CsvUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@Service
public class ContactService {
    Logger logger = LoggerFactory.getLogger(ContactService.class);

    private final ContactRepository contactRepository;
    private final UserRepository userRepository;

    public ContactService(ContactRepository contactRepository, UserRepository userRepository) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
    }
    public boolean saveAllImportedContactsToDatabase( MultipartFile file, Principal principal) {
            try {
                User currentUser = this.userRepository.findUserByUsername(principal.getName());
                List<Contact> contactList = CsvUtils.readCsvFile(file);
                contactList.forEach(contact -> contact.setUser(currentUser));
                contactRepository.saveAll(contactList);
                logger.info("All the contacts have been imported ");
                return  true;
            }
            catch (Exception e) {
                e.printStackTrace();
                logger.error("Something went wrong while importing contacts : ");
                return false;
            }
        }
}
