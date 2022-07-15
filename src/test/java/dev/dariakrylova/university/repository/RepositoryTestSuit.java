package dev.dariakrylova.university.repository;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        StudentRepositoryTest.class,
        CourseRepositoryTest.class,
        SubjectRepositoryTest.class
})
public class RepositoryTestSuit {
}
