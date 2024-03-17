package info.keeper.service;

import info.keeper.AppEnum;
import info.keeper.models.Contact;
import info.keeper.models.User;
import info.keeper.repositories.ContactRepository;
import info.keeper.repositories.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // only for ordering.
class ContactServiceTest {
    @Mock
    private ContactRepository contactRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ContactService serviceUnderTest;

    private User user;
    private Integer userId;
    private Integer contactId;
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
        contactId = 22;
    }
    @AfterEach
    void cleanUp() {
        // Any cleanup after each test.
    }

    @Test
    // @Order(2)
    @DisplayName("Assert that method returns the correct contacts for the given user.")
    @Tag("Contact_Service_Test")
    void findContactsByUser() {
        when(userRepository.findUserByUsername(username)).thenReturn(user);
        Pageable pageable = PageRequest.of(pageNumber, 5);
        when(contactRepository.findContactsByUser(userId, pageable)).thenReturn(getContactsWithPaginationForUser());
        Page<Contact> allContacts = serviceUnderTest.findContactsByUser(username, pageNumber);
        assertThat(allContacts.getTotalElements()).isEqualTo(expectedNumberOfContacts);
        Assertions.assertAll("Assert that returned elements have My Contact Name in name and 123456789 in phone number",
                () -> {
                    allContacts.getContent().forEach( contact -> {
                        assertTrue(contact.getName().contains("My Contact Name") &&
                                contact.getPhoneNumber().contains("123456789"));
                    });
                }
        );
    }

    @Test
    @DisplayName("Test for deleting contact/note provided that the note belongs to the current user.")
    @Tag("Contact_Service_Test")
    void deleteNote() throws IOException {
        Optional<Contact> contactOptional = getOptionalContact();
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn(username);
        when(this.contactRepository.findById(contactId)).thenReturn(contactOptional);
        when(this.userRepository.findUserByUsername(username)).thenReturn(user);
        // Perform delete operation
        AppEnum appEnum = serviceUnderTest.deleteNote(contactId, principal);
        // Assert that there was a correct return type.
        assertThat(appEnum).isEqualTo(AppEnum.OPERATION_SUCCESS);
        // Assert that contactRepository was invoked only once for delete method.
        verify(this.contactRepository, times(1)).delete(contactOptional.get());
    }

    @RepeatedTest(2) // repeat this two times.
    @Order(1)
    @DisplayName("Assert for required field's null values: With order 1, executes first.")
    @Tag("NullCheck")
    void assertNullValues() {
        assert this.contactRepository != null;
        assertThat(this.userRepository).isNotNull();
        assertThat(user.getId() >= 0);
        assertEquals(this.serviceUnderTest.getClass(), ContactService.class);
        assertTrue(this.contactRepository instanceof ContactRepository);
    }

    @DisplayName("Disabled until bug #42 has been resolved")
    @Tag("disabled")
    @Disabled()
    void disableTest() {
        // Nothing here
    }

    @NotNull
    private Optional<Contact> getOptionalContact() {
        Contact contact = new Contact();
        contact.setId(contactId);
        contact.setPhoneNumber("+1234567890");
        contact.setImageURL("http://abc.io");
        contact.setUser(user);
        return Optional.of(contact);
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