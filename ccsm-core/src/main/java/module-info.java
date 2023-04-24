module ccsm.core {
    requires java.sql;
    requires java.naming;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires jakarta.mail;

    opens stu.najah.se.core.entity to org.hibernate.orm.core;

    exports stu.najah.se.core;
    exports stu.najah.se.core.service;
    exports stu.najah.se.core.entity;
    exports stu.najah.se.core.dao to ccsm.core.test;
}
