package dev.dariakrylova.university.service;

import dev.dariakrylova.university.entity.Course;
import dev.dariakrylova.university.entity.Schedule;
import dev.dariakrylova.university.entity.Student;
import dev.dariakrylova.university.entity.Subject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ScheduleServiceTest {

    @MockBean
    private StudentService studentService;
    @MockBean
    private SubjectService subjectService;
    @Autowired
    private ScheduleService scheduleService;

    @Test
    void getSchedule() {
        Set<Student> studentsFiveJavaProgramming = new HashSet<>();
        Set<Student> studentsTwoJavaProgramming = new HashSet<>();
        Set<Student> studentsTwoMathFundamental = new HashSet<>();

        Set<Subject> subjectsFiveJavaProgramming = new HashSet<>();
        Set<Subject> subjectsTwoJavaProgramming = new HashSet<>();
        Set<Subject> subjectsTwoMathFundamental = new HashSet<>();

        Course courseFiveJavaProgramming =
                new Course(1L, 5, "Computer science", "Java programming", studentsFiveJavaProgramming, subjectsFiveJavaProgramming);

        Course courseTwoJavaProgramming =
                new Course(2L, 2, "Computer science", "Java programming", studentsTwoJavaProgramming, subjectsTwoJavaProgramming);

        Course courseTwoMathFundamental =
                new Course(3L, 2, "Computer science", "Math fundamental", studentsTwoMathFundamental, subjectsTwoMathFundamental);

        Subject math = new Subject(1L,
                "Math",
                LocalDate.of(2022, 1, 1),
                LocalDate.of(2022, 2, 1),
                "MONDAY",
                courseTwoMathFundamental);

        Subject history = new Subject(2L,
                "History",
                LocalDate.of(2022, 1, 1),
                LocalDate.of(2022, 2, 1),
                "MONDAY",
                courseTwoJavaProgramming);

        Subject dataScience = new Subject(4L,
                "Data science",
                LocalDate.of(2022, 5, 1),
                LocalDate.of(2022, 6, 1),
                "WEDNESDAY",
                courseFiveJavaProgramming);

        Subject javaProgramming = new Subject(5L,
                "Java programming",
                LocalDate.of(2022, 5, 1),
                LocalDate.of(2022, 6, 1),
                "WEDNESDAY",
                courseTwoJavaProgramming);

        subjectsFiveJavaProgramming.add(dataScience);
        subjectsTwoJavaProgramming.addAll(Set.of(history, javaProgramming));
        subjectsTwoMathFundamental.addAll(Set.of(math, history));

        Student studentAshley = new Student(
                1L, "Ashley", 2, courseTwoJavaProgramming);
        Student studentLindsey = new Student(
                2L, "Lindsey", 5, courseFiveJavaProgramming);
        Student studentAntony = new Student(
                3L, "Antony", 2, courseTwoMathFundamental);

        studentsFiveJavaProgramming.add(studentAshley);
        studentsTwoJavaProgramming.add(studentLindsey);
        studentsTwoMathFundamental.add(studentAntony);

        Mockito
                .when(studentService.getStudentById(1L))
                .thenReturn(studentAshley);
        Mockito
                .when(studentService.getStudentById(2L))
                .thenReturn(studentLindsey);
        Mockito
                .when(studentService.getStudentById(3L))
                .thenReturn(studentAntony);

        LocalDate today = LocalDate.of(2022, 1, 3);

        Mockito
                .when(subjectService.getAllAvailableSubjectsListByDate(today))
                .thenReturn(List.of(math, history));

        List<Schedule> expected = new ArrayList<>();
        expected.add(new Schedule(today.toString(),
                history.getWeekDay(),
                history.getSubjectName(),
                history.getStartDate().toString(),
                history.getStopDate().toString()));

        assertEquals(expected, scheduleService.getSchedule(1L, today));
    }
}