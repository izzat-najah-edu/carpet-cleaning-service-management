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
    public void testInvalidLogin() {
        assertThrows(IllegalArgumentException.class, () ->
                adminService.authenticate("invalid", "invalid"));

        assertFalse(authenticatorMock.isLoginRequested());
        assertFalse(authenticatorMock.isLogoutRequested());

        assertNull(adminService.getCurrentAdmin());
    }

    @Test
    public void testValidLogin() {
        adminService.authenticate("admin", "admin");

        assertTrue(authenticatorMock.isLoginRequested());
        assertFalse(authenticatorMock.isLogoutRequested());

        var admin = adminService.getCurrentAdmin();
        assertNotNull(admin);
        assertEquals(admin.getUsername(), "admin");
        assertEquals(admin.getPassword(), "admin");
    }

    @Test
    public void testLogout() {
        adminService.logout();

        assertFalse(authenticatorMock.isLoginRequested());
        assertTrue(authenticatorMock.isLogoutRequested());

        assertNull(adminService.getCurrentAdmin());
    }
}
