package dev.dariakrylova.university.service;

import dev.dariakrylova.university.entity.Subject;
import dev.dariakrylova.university.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SubjectService {

    public static final List<String> WEEK_DAYS = List.of(
            "SUNDAY",
            "MONDAY",
            "TUESDAY",
            "WEDNESDAY",
            "THURSDAY",
            "FRIDAY",
            "SATURDAY"
    );
    public static final String SUBJECT_WITH_GIVEN_NAME_ALREADY_EXISTS = "Subject with given name already exists";
    public static final String SUBJECT_NAME_TOO_SHORT = "Subject name too short";
    public static final String NO_SUBJECTS_FOUND = "No subjects found";
    public static final String SUBJECT_WITH_GIVEN_ID_NOT_FOUND = "Subject with given id not found";
    public static final String SUBJECT_WITH_GIVEN_ID_NOT_FOUND1 = "Subject with given id not found";
    public static final int SUBJECT_MIN_NAME_LENGTH = 1;
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getAllAvailableSubjectsListByDate(LocalDate date) {
        List<Subject> subjects = subjectRepository.findAll();

        if (subjects.size() == 0) {
            throw new NoSuchElementException(NO_SUBJECTS_FOUND);
        }

        List<Subject> result = new ArrayList<>();

        for (Subject subject : subjects) {
            if (subject.getStartDate().compareTo(date) * subject.getStopDate().compareTo(date) <= 0) {
                result.add(subject);
            }
        }

        return result;
    }

    public void createSubject(Subject subject) {
        Optional<Subject> studentOptional = subjectRepository.findSubjectBySubjectName(subject.getSubjectName());

        if (studentOptional.isPresent()) {
            throw new IllegalArgumentException(SUBJECT_WITH_GIVEN_NAME_ALREADY_EXISTS);
        }

        if (subject.getSubjectName().length() <= SUBJECT_MIN_NAME_LENGTH) {
            throw new IllegalArgumentException(SUBJECT_NAME_TOO_SHORT);
        }

        if (WEEK_DAYS.contains(subject.getWeekDay().toUpperCase())) {
            subjectRepository.save(subject);
        } else {
            throw new IllegalArgumentException("Week day is not walid");
        }
    }

    public List<Subject> getAllSubjectList() {
        List<Subject> result = subjectRepository.findAll();
        if (result.size() > 0) {
            return List.copyOf(result);

        } else {
            throw new NoSuchElementException(NO_SUBJECTS_FOUND);
        }
    }

    public Subject getSubjectById(long id) {
        Optional<Subject> studentOptional = subjectRepository.findById(id);

        if (studentOptional.isPresent()) {
            return studentOptional.get();
        } else {
            throw new NoSuchElementException(SUBJECT_WITH_GIVEN_ID_NOT_FOUND);
        }
    }

    public void updateSubject(Subject subject) {
        Optional<Subject> subjectOldOptional = subjectRepository.findById(subject.getId());

        if (subjectOldOptional.isPresent()) {
            if (subject.getSubjectName().length() <= SUBJECT_MIN_NAME_LENGTH) {
                throw new IllegalArgumentException(SUBJECT_NAME_TOO_SHORT);
            }

            subjectRepository.save(subject);
        } else {
            throw new NoSuchElementException(SUBJECT_WITH_GIVEN_ID_NOT_FOUND1);
        }
    }

    public void deleteSubjectById(long id) {
        Optional<Subject> subjectOptional = subjectRepository.findById(id);

        if (subjectOptional.isPresent()) {
            subjectRepository.deleteById(id);
        } else {
            throw new NoSuchElementException(SUBJECT_WITH_GIVEN_ID_NOT_FOUND1);
        }
    }
}
