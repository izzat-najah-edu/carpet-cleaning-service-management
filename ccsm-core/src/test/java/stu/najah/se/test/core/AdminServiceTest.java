package stu.najah.se.test.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import stu.najah.se.core.UserSessionListener;
import stu.najah.se.core.dao.AdminDAO;
import stu.najah.se.core.entity.AdminEntity;
import stu.najah.se.core.service.AdminService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class AdminServiceTest {

    private static AdminDAO adminDAO;

    private AdminService adminService;

    private UserSessionListener sessionListener;

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
        sessionListener = Mockito.mock(UserSessionListener.class);
        adminService = new AdminService(adminDAO, sessionListener);
    }

    @Test
    public void testLoginInvalidUsername() {
        assertThrows(IllegalArgumentException.class, () ->
                adminService.authenticate("invalid", "password"));
        verify(sessionListener, never()).login();
        verify(sessionListener, never()).logout();
        assertTrue(adminService.getCurrentAdmin().isEmpty());
    }

    @Test
    public void testLoginInvalidPassword() {
        assertThrows(IllegalArgumentException.class, () ->
                adminService.authenticate("username", "invalid"));
        verify(sessionListener, never()).login();
        verify(sessionListener, never()).logout();
        assertTrue(adminService.getCurrentAdmin().isEmpty());
    }

    @Test
    public void testLoginValid() {
        adminService.authenticate("username", "password");
        verify(sessionListener, times(1)).login();
        verify(sessionListener, never()).logout();
        var optional = adminService.getCurrentAdmin();
        assertTrue(optional.isPresent());
        var admin = optional.get();
        assertEquals("username", admin.getUsername());
        assertEquals("password", admin.getPassword());
    }

    @Test
    public void testLogout() {
        adminService.logout();
        verify(sessionListener, never()).login();
        verify(sessionListener, times(1)).logout();
        assertTrue(adminService.getCurrentAdmin().isEmpty());
    }
}
