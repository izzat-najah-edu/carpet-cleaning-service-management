package stu.najah.se.test.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import stu.najah.se.core.Authenticator;
import stu.najah.se.core.dao.AdminDAO;
import stu.najah.se.core.entity.AdminEntity;
import stu.najah.se.core.service.AdminService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class AdminServiceTest {

    private static AdminDAO adminDAO;

    private AdminService adminService;

    private Authenticator authenticator;

    @BeforeAll
    public static void initializeAdminMocks() {
        adminDAO = Mockito.mock(AdminDAO.class);
        AdminEntity testAdmin = new AdminEntity();
        testAdmin.setUsername("username");
        testAdmin.setPassword("password");
        when(adminDAO.get("username")).thenReturn(testAdmin);
    }

    @BeforeEach
    public void setUp() {
        authenticator = Mockito.mock(Authenticator.class);
        adminService = new AdminService(adminDAO, authenticator);
    }

    @Test
    public void testLoginInvalidUsername() {
        assertThrows(IllegalArgumentException.class, () ->
                adminService.authenticate("invalid", "password"));
        verify(authenticator, never()).login();
        verify(authenticator, never()).logout();
        assertTrue(adminService.getCurrentAdmin().isEmpty());
    }

    @Test
    public void testLoginInvalidPassword() {
        assertThrows(IllegalArgumentException.class, () ->
                adminService.authenticate("username", "invalid"));
        verify(authenticator, never()).login();
        verify(authenticator, never()).logout();
        assertTrue(adminService.getCurrentAdmin().isEmpty());
    }

    @Test
    public void testLoginValid() {
        adminService.authenticate("username", "password");
        verify(authenticator, times(1)).login();
        verify(authenticator, never()).logout();
        var optional = adminService.getCurrentAdmin();
        assertTrue(optional.isPresent());
        var admin = optional.get();
        assertEquals("username", admin.getUsername());
        assertEquals("password", admin.getPassword());
    }

    @Test
    public void testLogout() {
        adminService.logout();
        verify(authenticator, never()).login();
        verify(authenticator, times(1)).logout();
        assertTrue(adminService.getCurrentAdmin().isEmpty());
    }
}
