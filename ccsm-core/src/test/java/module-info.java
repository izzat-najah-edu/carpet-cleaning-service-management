module ccsm.core.test {
    requires ccsm.core;
    requires org.junit.jupiter.api;
    requires org.mockito;
    requires junit;

    exports stu.najah.se.test.core.service to org.junit.platform.commons, junit;
}
