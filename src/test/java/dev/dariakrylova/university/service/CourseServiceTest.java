package dev.dariakrylova.university.service;

import dev.dariakrylova.university.entity.Course;
import dev.dariakrylova.university.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CourseServiceTest {

    @MockBean
    private CourseRepository courseRepository;
    @Autowired
    private CourseService courseService;

    @Test
    void createCourseWhenCourseAlreadyExists() {
        Mockito
                .when(courseRepository.findCourseByFacultyNameAndSpecialityNameAndCourseNumber("123456", "123", 5))
                .thenReturn(Optional.of(new Course()));

        assertThrows(
                IllegalArgumentException.class, () ->
                        courseService.createCourse(
                                new Course(1L, 5, "123456", "123", new HashSet<>(), new HashSet<>()))
        );
    }

    @Test
    void createCourseWhenFacultyNameTooShort() {
        Mockito
                .when(courseRepository.findCourseByCourseNumber(5))
                .thenReturn(Optional.empty());

        assertThrows(
                IllegalArgumentException.class, () ->
                        courseService.createCourse(
                                new Course(1L, 5, "12345", "123", new HashSet<>(), new HashSet<>())
                        ));
    }

    @Test
    void createCourseWhenSpecialityNameTooShort() {

        assertThrows(
                IllegalArgumentException.class, () ->
                        courseService.createCourse(
                                new Course(1L, 5, "123456", "12", new HashSet<>(), new HashSet<>()))
        );
    }

    @Test
    void createCourseWhenCourseNumberAndMoreThan5() {

        assertThrows(
                IllegalArgumentException.class, () ->
                        courseService.createCourse(
                                new Course(1L, 6, "123456", "123", new HashSet<>(), new HashSet<>()))
        );
    }

    @Test
    void createCourseWhenCourseNumberLessThan1() {

        assertThrows(
                IllegalArgumentException.class, () ->
                        courseService.createCourse(
                                new Course(1L, 0, "123456", "123", new HashSet<>(), new HashSet<>()))
        );
    }

    @Test
    void createCourse() {

        assertDoesNotThrow(() -> courseService.createCourse(
                new Course(1L, 5, "123456", "123", new HashSet<>(), new HashSet<>()))
        );
    }

    @Test
    void getAllCourseListWhenEmpty() {
        Mockito
                .when(courseRepository.findAll())
                .thenReturn(new ArrayList<>());

        assertThrows(NoSuchElementException.class, () -> courseService.getAllCourseList());
    }

    @Test
    void getAllCourseListWhenNotEmpty() {
        List<Course> notEmpty = List.of(new Course());
        Mockito
                .when(courseRepository.findAll())
                .thenReturn(notEmpty);

        assertDoesNotThrow(() -> courseService.getAllCourseList());
    }

    @Test
    void getCourseByIdWhenNotFound() {

        assertThrows(NoSuchElementException.class, () -> courseService.getCourseById(1L));
    }

    @Test
    void getCourseByIdWhenFound() {
        Mockito
                .when(courseRepository.findById(1L))
                .thenReturn(Optional.of(new Course()));

        assertDoesNotThrow(() -> courseService.getCourseById(1L));
    }

    @Test
    void updateCourseWhenFacultyNameTooShort() {

        assertThrows(
                IllegalArgumentException.class, () ->
                        courseService.updateCourse(
                                new Course(1L, 5, "12345", "123", new HashSet<>(), new HashSet<>()))
        );
    }

    @Test
    void updateCourseWhenSpecialityNameTooShort() {

        assertThrows(
                IllegalArgumentException.class, () ->
                        courseService.updateCourse(
                                new Course(1L, 5, "123456", "12", new HashSet<>(), new HashSet<>()))
        );
    }

    @Test
    void updateCourseWhenCourseNumberAndMoreThan5() {

        assertThrows(
                IllegalArgumentException.class, () ->
                        courseService.updateCourse(
                                new Course(1L, 6, "123456", "123", new HashSet<>(), new HashSet<>()))
        );
    }

    @Test
    void updateCourseWhenCourseNumberLessThan1() {

        assertThrows(
                IllegalArgumentException.class, () ->
                        courseService.updateCourse(
                                new Course(1L, 0, "123456", "123", new HashSet<>(), new HashSet<>()))
        );
    }

    @Test
    void deleteCourseByIdWhenNotFound() {

        assertThrows(NoSuchElementException.class, () -> courseService.deleteCourseById(1L));
    }

    @Test
    void deleteCourseByIdWhenFound() {
        Mockito
                .when(courseRepository.findById(1L))
                .thenReturn(Optional.of(new Course()));

        assertDoesNotThrow(() -> courseService.deleteCourseById(1L));
    }
}