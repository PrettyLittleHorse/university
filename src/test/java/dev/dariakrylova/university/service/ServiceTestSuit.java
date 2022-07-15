package dev.dariakrylova.university.service;


import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        CourseServiceTest.class,
        ScheduleServiceTest.class,
        StudentServiceTest.class,
        SubjectServiceTest.class,
})
public class ServiceTestSuit {
}
