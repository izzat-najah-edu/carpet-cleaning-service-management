package stu.najah.se.test.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stu.najah.se.core.ServiceManager;
import stu.najah.se.core.service.AdminService;

import static org.junit.jupiter.api.Assertions.*;

public class AdminServiceTest {

    @BeforeAll
    public static void initialize() {
        ServiceManager.initializeAdminService(AuthenticatorMock.getInstance());
    }

    private final AuthenticatorMock authenticatorMock = AuthenticatorMock.getInstance();
    private final AdminService adminService = AdminService.getInstance();

    @BeforeEach
    public void reset() {
        authenticatorMock.resetRequests();
    }

    @Test
    public void testLoginInvalidUsername() {
        assertThrows(IllegalArgumentException.class, () ->
                adminService.authenticate("invalid", "admin"));
        assertFalse(authenticatorMock.isLoginRequested());
        assertFalse(authenticatorMock.isLogoutRequested());
        assertNull(adminService.getCurrentAdmin());
    }

    @Test
    public void testLoginInvalidPassword() {
        assertThrows(IllegalArgumentException.class, () ->
                adminService.authenticate("admin", "invalid"));
        assertFalse(authenticatorMock.isLoginRequested());
        assertFalse(authenticatorMock.isLogoutRequested());
        assertNull(adminService.getCurrentAdmin());
    }

    @Test
    public void testLoginValid() {
        adminService.authenticate("admin", "admin");
        assertTrue(authenticatorMock.isLoginRequested());
        assertFalse(authenticatorMock.isLogoutRequested());
        var admin = adminService.getCurrentAdmin();
        assertNotNull(admin);
        assertEquals("admin", admin.getUsername());
        assertEquals("admin", admin.getPassword());
    }

    @Test
    public void testLogout() {
        adminService.logout();
        assertFalse(authenticatorMock.isLoginRequested());
        assertTrue(authenticatorMock.isLogoutRequested());
        assertNull(adminService.getCurrentAdmin());
    }
}
