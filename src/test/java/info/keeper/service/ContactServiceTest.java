package info.keeper.service;

import info.keeper.models.Contact;
import info.keeper.models.User;
import info.keeper.repositories.ContactRepository;
import info.keeper.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ContactServiceTest {
    @Mock
    private ContactRepository contactRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ContactService serviceUnderTest;

    private User user;
    private Integer userId;
    private String username;
    private Integer pageNumber;
    private Long expectedNumberOfContacts;

    @BeforeEach
    void setUp() {
        // Any setup before each test.
        username = "john.doe@gmail.com";
        userId = 101;
        pageNumber = 1;
        expectedNumberOfContacts = 5L;
        user = new User();
        user.setEmail(username);
        user.setId(userId);
    }
    @AfterEach
    void cleanUp() {
        // Any cleanup after each test.
    }

    @Test
    void findContactsByUser() {
        Mockito.when(userRepository.findUserByUsername(username)).thenReturn(user);
        Pageable pageable = PageRequest.of(pageNumber, 5);
        Mockito.when(contactRepository.findContactsByUser(userId, pageable)).thenReturn(getContactsWithPaginationForUser());
        Page<Contact> allContacts = serviceUnderTest.findContactsByUser(username, pageNumber);
        assertThat(allContacts.getTotalElements()).isEqualTo(expectedNumberOfContacts);
        Assertions.assertAll("Assert that returned elements have My Contact Name in name and 123456789 in phone number",
                () -> {
                    allContacts.getContent().forEach( contact -> {
                        Assertions.assertTrue(contact.getName().contains("My Contact Name") &&
                                contact.getPhoneNumber().contains("123456789"));
                    });
                }
        );
    }

    private Page<Contact> getContactsWithPaginationForUser() {
        List<Contact> contacts = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            String contactName = "My Contact Name - " + i;
            String phoneNumber = "123456789" + i;
            Contact contact = new Contact();
            contact.setId(i);
            contact.setName(contactName);
            contact.setPhoneNumber(phoneNumber);
            contacts.add(contact);
        }
        // Create a mock Page object containing the contacts
        PageImpl<Contact> pagedContacts = new PageImpl<>(contacts);
        return pagedContacts;
    }
}