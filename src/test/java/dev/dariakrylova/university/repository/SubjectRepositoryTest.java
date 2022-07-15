package dev.dariakrylova.university.repository;

import dev.dariakrylova.university.entity.Course;
import dev.dariakrylova.university.entity.Student;
import dev.dariakrylova.university.entity.Subject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.PreRemove;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SubjectRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findSubjectBySubjectName() {
        Course course4 = new Course(4, "Faculty 1", "Speciality 1", new HashSet<>(), new HashSet<>());
        Course course3 = new Course(3, "Faculty 2", "Speciality 2", new HashSet<>(), new HashSet<>());
        Course course1 = new Course(1, "Faculty 4", "Speciality 4", new HashSet<>(), new HashSet<>());

        Subject math = new Subject(
                "Math",
                LocalDate.of(2022, 1, 1),
                LocalDate.of(2022, 4, 22),
                "Friday",
                course1);

        Subject history = new Subject(
                "History ",
                LocalDate.of(2022, 2, 1),
                LocalDate.of(2022, 3, 1),
                "Friday",
                course3);

        Subject computerScience = new Subject(
                "Computer science",
                LocalDate.of(2022, 4, 1),
                LocalDate.of(2022, 6, 21),
                "Friday",
                course4);

        courseRepository.saveAllAndFlush(List.of(course1, course3, course4));
        subjectRepository.saveAllAndFlush(List.of(math, history, computerScience));

        Subject actual = subjectRepository.findSubjectBySubjectName("History ").get();

        assertEquals(history, actual);
    }
}