package dev.dariakrylova.university.service;

import dev.dariakrylova.university.entity.Course;
import dev.dariakrylova.university.entity.Student;
import dev.dariakrylova.university.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentServiceTest {

    @MockBean
    private StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;

    @Test
    void createStudentWhenStudentWithGivenIdAlreadyExists() {
        Mockito
                .when(studentRepository.findById(1L))
                .thenReturn(Optional.of(new Student()));

        assertThrows(IllegalArgumentException.class, () -> studentService.createStudent(new Student(1L, "", 5, new Course())));
    }

    @Test
    void createStudentWhenStudentHaveIncorrectName() {
        Mockito
                .when(studentRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> studentService.createStudent(new Student(1L, "1234", 5, new Course())));
    }

    @Test
    void createStudentWhenStudentHaveCorrectName() {
        Mockito
                .when(studentRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertDoesNotThrow(() -> studentService.createStudent(new Student(1L, "12345", 5, new Course())));
    }

    @Test
    void getAllStudentListWhenListNotEmpty() {
        List<Student> notEmpty = List.of(new Student());
        Mockito
                .when(studentRepository.findAll())
                .thenReturn(notEmpty);

        assertDoesNotThrow(() -> studentService.getAllStudentList());
    }

    @Test
    void getAllStudentListWhenListEmpty() {
        Mockito
                .when(studentRepository.findAll())
                .thenReturn(new ArrayList<>());

        assertThrows(NoSuchElementException.class, () -> studentService.getAllStudentList());
    }

    @Test
    void getStudentByIdWhenStudentNotFound() {
        Mockito
                .when(studentRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> studentService.getStudentById(1L));
    }

    @Test
    void getStudentByIdWhenStudentFound() {
        Mockito
                .when(studentRepository.findById(1L))
                .thenReturn(Optional.of(new Student()));

        assertDoesNotThrow(() -> studentService.getStudentById(1L));
    }

    @Test
    void getStudentIdByNameWhenStudentNotFound() {
        Mockito
                .when(studentRepository.findStudentByStudentName("1234"))
                .thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> studentService.getStudentIdByName("1234"));
    }

    @Test
    void getStudentIdByNameWhenStudentFound() {
        Mockito
                .when(studentRepository.findStudentByStudentName("1234"))
                .thenReturn(Optional.of(new Student()));

        assertDoesNotThrow(() -> studentService.getStudentIdByName("1234"));
    }


    @Test
    void updateStudentWhenStudentNotFound() {
        Mockito
                .when(studentRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> studentService.updateStudent(new Student(1L, "", 5, new Course())));
    }

    @Test
    void updateStudentWhenStudentHaveIncorrectName() {
        Mockito
                .when(studentRepository.findById(1L))
                .thenReturn(Optional.of(new Student()));

        assertThrows(IllegalArgumentException.class, () -> studentService.updateStudent(new Student(1L, "1234", 5, new Course())));
    }

    @Test
    void updateStudentWhenStudentHaveCorrectName() {
        Mockito
                .when(studentRepository.findById(1L))
                .thenReturn(Optional.of(new Student()));

        assertDoesNotThrow(() -> studentService.updateStudent(new Student(1L, "12345", 5, new Course())));
    }

    @Test
    void deleteStudentByIdWhenStudentNotFound() {
        Mockito
                .when(studentRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> studentService.deleteStudentById(1L));
    }

    @Test
    void deleteStudentByIdWhenStudentFound() {
        Mockito
                .when(studentRepository.findById(1L))
                .thenReturn(Optional.of(new Student()));

        assertDoesNotThrow(() -> studentService.deleteStudentById(1L));
    }
}