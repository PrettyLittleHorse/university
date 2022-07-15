package dev.dariakrylova.university.service;

import dev.dariakrylova.university.entity.Student;
import dev.dariakrylova.university.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StudentService {

    public static final String STUDENT_WITH_GIVEN_NAME_ALREADY_EXISTS = "Student with given name already exists";
    public static final String STUDENT_NAME_TOO_SHORT = "Student name too short";
    public static final String NO_STUDENTS_FOUND = "No students found";
    public static final String STUDENT_WITH_GIVEN_ID_NOT_FOUND = "Student with given id not found";
    public static final int STUDENT_NAME_MIN_LENGTH = 4;
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void createStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByStudentName(student.getStudentName());

        if (studentOptional.isPresent()) {
            throw new IllegalArgumentException(STUDENT_WITH_GIVEN_NAME_ALREADY_EXISTS);
        }

        if (student.getStudentName().length() <= STUDENT_NAME_MIN_LENGTH) {
            throw new IllegalArgumentException(STUDENT_NAME_TOO_SHORT);
        }

        studentRepository.save(student);
    }

    public List<Student> getAllStudentList() {
        List<Student> result = studentRepository.findAll();
        if (result.size() > 0) {
            return List.copyOf(result);

        } else {
            throw new NoSuchElementException(NO_STUDENTS_FOUND);
        }
    }

    public Student getStudentById(long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);

        if (studentOptional.isPresent()) {
            return studentOptional.get();
        } else {
            throw new NoSuchElementException(STUDENT_WITH_GIVEN_ID_NOT_FOUND);
        }
    }

    public Long getStudentIdByName(String name) {
        Optional<Student> studentId = studentRepository.findStudentByStudentName(name);

        if (studentId.isEmpty()) {
            throw new NoSuchElementException(STUDENT_WITH_GIVEN_ID_NOT_FOUND);
        }

        return studentId.get().getId();
    }

    public void updateStudent(Student student) {
        Optional<Student> studentOldOptional = studentRepository.findById(student.getId());

        if (studentOldOptional.isPresent()) {
            if (student.getStudentName().length() <= STUDENT_NAME_MIN_LENGTH) {
                throw new IllegalArgumentException(STUDENT_NAME_TOO_SHORT);
            }

            studentRepository.save(student);
        } else {
            throw new NoSuchElementException(STUDENT_WITH_GIVEN_ID_NOT_FOUND);
        }
    }

    public void deleteStudentById(long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);

        if (studentOptional.isPresent()) {
            studentRepository.deleteById(id);
        } else {
            throw new NoSuchElementException(STUDENT_WITH_GIVEN_ID_NOT_FOUND);
        }
    }
}
