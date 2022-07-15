package dev.dariakrylova.university;

import dev.dariakrylova.university.repository.CourseRepositoryTest;
import dev.dariakrylova.university.repository.StudentRepositoryTest;
import dev.dariakrylova.university.repository.SubjectRepositoryTest;
import dev.dariakrylova.university.service.CourseServiceTest;
import dev.dariakrylova.university.service.ScheduleServiceTest;
import dev.dariakrylova.university.service.StudentServiceTest;
import dev.dariakrylova.university.service.SubjectServiceTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        CourseServiceTest.class,
        ScheduleServiceTest.class,
        StudentServiceTest.class,
        SubjectServiceTest.class,
        StudentRepositoryTest.class,
        CourseRepositoryTest.class,
        SubjectRepositoryTest.class
})
public class UniversityAppTestSuite {
}
