package stu.najah.se.core;

import stu.najah.se.core.dao.AdminDAO;
import stu.najah.se.core.service.AdminService;

public abstract class ServicesManager {

    private static AdminService adminService;

    public static void initializeAdminService(Authenticator authenticator) throws IllegalStateException {
        if (adminService == null) {
            adminService = new AdminService(new AdminDAO(), authenticator);
        } else {
            throw new IllegalStateException("Admin service already initialized!");
        }
    }

    public static AdminService getAdminService() throws IllegalStateException {
        if (adminService == null) {
            throw new IllegalStateException("Admin service not initialized!");
        } else {
            return adminService;
        }
    }
}
