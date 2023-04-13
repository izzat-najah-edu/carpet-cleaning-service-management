module ccsm.core.test {
    requires ccsm.core;
    requires junit;
    requires org.junit.jupiter.api;
    requires org.mockito;

    exports stu.najah.se.test.core to org.junit.platform.commons;
}
