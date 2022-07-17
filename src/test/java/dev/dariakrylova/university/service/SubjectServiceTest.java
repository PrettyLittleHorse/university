package dev.dariakrylova.university.service;

import dev.dariakrylova.university.entity.Course;
import dev.dariakrylova.university.entity.Subject;
import dev.dariakrylova.university.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SubjectServiceTest {

    @MockBean
    private SubjectRepository subjectRepository;
    @Autowired
    private SubjectService subjectService;

    @Test
    void getAllAvailableSubjectsListByDateWhenListEmpty() {

        Mockito
                .when(subjectRepository.findAll())
                .thenReturn(new ArrayList<>());

        assertThrows(NoSuchElementException.class, () -> subjectService.getAllAvailableSubjectsListByDate(LocalDate.now()));
    }

    @Test
    void getAllAvailableSubjectsByDateWhenListNotEmpty() {


        Subject math = new Subject(1L,
                "Math",
                LocalDate.of(2022, 1, 1),
                LocalDate.of(2022, 4, 22),
                "Friday",
                new Course());

        Subject history = new Subject(2L,
                "History",
                LocalDate.of(2022, 2, 1),
                LocalDate.of(2022, 3, 1),
                "Friday",
                new Course());

        Subject computerScience = new Subject(3L,
                "Computer science",
                LocalDate.of(2022, 4, 1),
                LocalDate.of(2022, 6, 21),
                "Friday",
                new Course());

        List<Subject> subjects = List.of(math, history, computerScience);

        Mockito
                .when(subjectRepository.findAll())
                .thenReturn(subjects);

        List<Subject> expected = List.of(math, history);

        assertEquals(expected, subjectService.getAllAvailableSubjectsListByDate(LocalDate.of(2022, 3, 1)));
    }


    @Test
    void createSubjectWhenIdAlreadyExists() {
        Mockito
                .when(subjectRepository.findSubjectBySubjectName("123"))
                .thenReturn(Optional.of(new Subject()));

        assertThrows(IllegalArgumentException.class, () -> subjectService.createSubject(new Subject(1L, "123", LocalDate.now(), LocalDate.now(), "Friday", new Course())));
    }

    @Test
    void createSubjectWhenNameLessThen2() {

        assertThrows(IllegalArgumentException.class, () -> subjectService.createSubject(new Subject(1L, "", LocalDate.now(), LocalDate.now(), "Friday", new Course())));
    }

    @Test
    void createSubjectWhenWeekDayIsNotValid() {

        assertThrows(IllegalArgumentException.class, () -> subjectService.createSubject(new Subject(1L, "123", LocalDate.now(), LocalDate.now(), "Friday11", new Course())));
    }

    @Test
    void createSubjectWhenIdNotFoundButNameBiggerThen2() {

        assertDoesNotThrow(() -> subjectService.createSubject(new Subject(1L, "Tw", LocalDate.now(), LocalDate.now(), "Friday", new Course())));
    }

    @Test
    void getAllSubjectListWhenListEmpty() {
        Mockito
                .when(subjectRepository.findAll())
                .thenReturn(new ArrayList<>());

        assertThrows(NoSuchElementException.class, () -> subjectService.getAllSubjectList());
    }

    @Test
    void getAllSubjectListWhenListNotEmpty() {
        List<Subject> notEmpty = new ArrayList<>();
        notEmpty.add(new Subject());

        Mockito
                .when(subjectRepository.findAll())
                .thenReturn(notEmpty);

        assertDoesNotThrow(() -> subjectService.getAllSubjectList());
    }

    @Test
    void getSubjectByIdWhenSubjectNotFound() {

        assertThrows(NoSuchElementException.class, () -> subjectService.getSubjectById(1L));
    }

    @Test
    void getSubjectByIdWhenSubjectFound() {
        Mockito
                .when(subjectRepository.findById(1L))
                .thenReturn(Optional.of(new Subject()));

        assertDoesNotThrow(() -> subjectService.getSubjectById(1L));
    }

    @Test
    void updateSubjectWhenSubjectNotFound() {

        assertThrows(NoSuchElementException.class, () -> subjectService.updateSubject(new Subject()));
    }

    @Test
    void updateSubjectWhenSubjectFoundAndHaveIncorrectName() {
        Mockito
                .when(subjectRepository.findById(1L))
                .thenReturn(Optional.of(new Subject()));

        assertThrows(IllegalArgumentException.class, () -> subjectService.updateSubject(new Subject(1L, "", LocalDate.now(), LocalDate.now(), "Friday", new Course())));
    }

    @Test
    void updateSubjectWhenSubjectFoundAndHaveCorrectName() {
        Mockito
                .when(subjectRepository.findById(1L))
                .thenReturn(Optional.of(new Subject()));

        assertDoesNotThrow(() -> subjectService.updateSubject(new Subject(1L, "Co", LocalDate.now(), LocalDate.now(), "Friday", new Course())));
    }

    @Test
    void deleteSubjectByIdWhenSubjectNotFound() {

        assertThrows(NoSuchElementException.class, () -> subjectService.deleteSubjectById(1L));
    }

    @Test
    void deleteSubjectByIdWhenSubjectFound() {
        Mockito
                .when(subjectRepository.findById(1L))
                .thenReturn(Optional.of(new Subject()));

        assertDoesNotThrow(() -> subjectService.deleteSubjectById(1L));
    }
}