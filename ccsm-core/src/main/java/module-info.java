module ccsm.core {
    requires java.sql;
    requires java.naming;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;

    opens stu.najah.se.core.entity to org.hibernate.orm.core;

    exports stu.najah.se.core;
    exports stu.najah.se.core.service;

    // this connection should be cut...
    exports stu.najah.se.core.entity;
    exports stu.najah.se.core.dao;
}
