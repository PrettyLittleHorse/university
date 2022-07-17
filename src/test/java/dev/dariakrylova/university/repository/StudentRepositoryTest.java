package dev.dariakrylova.university.repository;

import dev.dariakrylova.university.entity.Course;
import dev.dariakrylova.university.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void findStudentIdByStudentName() {
        Course course1 = new Course(1, "Faculty 4", "Speciality 4", new HashSet<>(), new HashSet<>());
        Course course4 = new Course(4, "Faculty 1", "Speciality 1", new HashSet<>(), new HashSet<>());
        Course course3 = new Course(3, "Faculty 2", "Speciality 2", new HashSet<>(), new HashSet<>());
        Course course5 = new Course(5, "Faculty 3", "Speciality 3", new HashSet<>(), new HashSet<>());

        Student sindy = new Student("Sindy", 1, course1);
        Student lana = new Student("Lana", 3, course3);
        Student petra = new Student("Petra", 4, course4);
        Student clark = new Student("Clark", 5, course5);
        //Using constructor of Student without id cause id is generated value

        courseRepository.saveAllAndFlush(List.of(course1, course3, course4, course5));
        studentRepository.saveAllAndFlush(List.of(petra, lana, clark, sindy));


        Long actual = studentRepository.findStudentByStudentName("Lana").get().getId();

        assertEquals(lana.getId(), actual);
    }
}