package stu.najah.se.data.dao;

import stu.najah.se.data.entity.AdminEntity;

public class AdminDAO extends FullDAO<AdminEntity> {

    /**
     * Constructs a new AdminDAO instance.
     */
    public AdminDAO() {
        super(AdminEntity.class);
    }
}
