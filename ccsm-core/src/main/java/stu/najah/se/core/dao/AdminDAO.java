package stu.najah.se.core.dao;

import stu.najah.se.core.entity.AdminEntity;

public class AdminDAO extends FullDAO<AdminEntity> {

    /**
     * Constructs a new AdminDAO instance.
     */
    public AdminDAO() {
        super(AdminEntity.class);
    }
}
