package stu.najah.se.dao;

import stu.najah.se.entity.AdminEntity;

public class AdminDAO extends FullDAO<AdminEntity> {

    /**
     * Constructs a new AdminDAO instance.
     */
    public AdminDAO() {
        super(AdminEntity.class);
    }
}
