package dev.dariakrylova.university.repository;

import dev.dariakrylova.university.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

    @Test
    void findCourseByCourseNumber() {
        Course courseFiveJavaProgramming =
                new Course(5, "Computer science", "Java programming", new HashSet<>(), new HashSet<>());

        Course courseTwoJavaProgramming =
                new Course(2, "Computer science", "Java programming", new HashSet<>(), new HashSet<>());

        Course courseTwoMathFundamental =
                new Course(2, "Computer science", "Math fundamental", new HashSet<>(), new HashSet<>());

        courseRepository.saveAllAndFlush(List.of(courseFiveJavaProgramming, courseTwoJavaProgramming, courseTwoMathFundamental));

        Course actual = courseRepository.findCourseByCourseNumber(5).get();
        assertEquals(courseFiveJavaProgramming, actual);
    }
}