package info.keeper.service;

import info.keeper.AppEnum;
import info.keeper.models.Contact;
import info.keeper.models.User;
import info.keeper.repositories.ContactRepository;
import info.keeper.repositories.UserRepository;
import info.keeper.utils.CsvUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

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

    public AppEnum deleteNote(int id, Principal principal) throws IOException {
        Optional<Contact> optionalContact = contactRepository.findById(id);
        Contact contact = optionalContact.get();

        User user = userRepository.findUserByUsername(principal.getName());
        if(user.getId() != contact.getUser().getId()) {
            return AppEnum.NOT_AUTHORIZED;
        }
        // delete image also if it's not the default image
        if(!(contact.getImageURL()).equals("default.png")) {
            File folderToDeleteFile = new ClassPathResource("static/images").getFile();
            File file = new File(folderToDeleteFile, contact.getImageURL());
            file.delete();
        }
        contactRepository.delete(contact);
        return AppEnum.OPERATION_SUCCESS;
    }

    public Page<Contact> findContactsByUser(String username, int pageNumber ) {
        User user = userRepository.findUserByUsername(username);
        //Pageable has current page and number of notes per page
        Pageable pageable = PageRequest.of(pageNumber, 5); // 5 notes per page
        Page<Contact> allContacts = contactRepository.findContactsByUser(user.getId(), pageable);
        return allContacts;
    }
}
