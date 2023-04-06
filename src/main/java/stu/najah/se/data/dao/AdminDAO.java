package stu.najah.se.data.dao;

import stu.najah.se.data.entity.AdminEntity;

public class AdminDAO extends DAO<AdminEntity> {

    /**
     * @param username of the admin
     * @return the AdminEntity with the given username
     * or null if it's not found
     */
    public AdminEntity get(String username) {
        var session = Database.createSession();
        var admin = session.find(AdminEntity.class, username);
        session.close();
        return admin;
    }
}