module ccsm.core {
    requires java.sql;
    requires java.naming;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;

    opens stu.najah.se.entity to org.hibernate.orm.core;

    exports stu.najah.se.service;
}
