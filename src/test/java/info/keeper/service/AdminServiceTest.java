package info.keeper.service;

import info.keeper.models.AdminMessage;
import info.keeper.repositories.AdminRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AdminServiceTest {
    @InjectMocks
    AdminService adminService;

    @Mock
    AdminRepository adminRepository;
    @Mock
    Principal principal;

    private String username;

    @BeforeEach
    void setUp() {
        this.username = "smith@hello.io";
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveAdminMessage() {
        // Principal principal = mock(Principal.class); // can be mocked this way as well.
        when(principal.getName()).thenReturn(this.username);

        AdminMessage adminMessage = new AdminMessage();
        adminMessage.setMessage("Admin message: From Testing Service.");
        adminMessage.setPostedBy(this.username.split("@")[0]);
        adminMessage.setId(100);

        // when this is called
        this.adminService.saveAdminMessage(adminMessage, principal);
        // verify the result.
        verify(adminRepository, times(1)).save(any(AdminMessage.class)); // saved entity was the instance of AdminMessage
        verify(adminRepository, times(1)).save(adminMessage);
        assertNotNull(adminMessage.getDate()); // make sure date was set
        assertEquals("smith", adminMessage.getPostedBy());
    }
}