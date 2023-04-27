module ccsm.core.test {
    requires ccsm.core;
    requires org.junit.jupiter.api;
    requires org.mockito;
    requires junit;

    opens stu.najah.se.test.core.service to junit, org.junit.platform.commons;
    opens stu.najah.se.test.core.service.aya to junit, org.junit.platform.commons;
}
