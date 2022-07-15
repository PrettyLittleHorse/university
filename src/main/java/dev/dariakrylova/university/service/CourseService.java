package dev.dariakrylova.university.service;

import dev.dariakrylova.university.entity.Course;
import dev.dariakrylova.university.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CourseService {

    public static final String COURSE_WITH_GIVEN_NAME_ALREADY_EXISTS = "Course with given name already exists";
    public static final String COURSE_NUMBER_CAN_NOT_BE_MORE_THAN_5_OR_LESS_THAN_1 = "Course number can not be more than 5 or less than 1";
    public static final String NO_COURSES_FOUND = "No courses found";
    public static final String COURSE_WITH_GIVEN_ID_NOT_FOUND = "Course with given id not found";
    public static final String FACULTY_NAME_TOO_SHORT = "Faculty name too short";
    public static final String SPECIALITY_NAME_IS_TOO_SHORT = "Speciality name is too short";
    public static final int FACULTY_NAME_MIN_LENGTH = 6;
    public static final int SPECIALITY_NAME_LENGTH = 3;
    public static final int COURSE_NUMBER_MIN = 1;
    public static final int COURSE_NUMBER_MAX = 5;

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void createCourse(Course course) {
        Optional<Course> courseOptional = courseRepository.findCourseByCourseNumber(course.getCourseNumber());

        if (courseOptional.isPresent()) {
            throw new IllegalArgumentException(COURSE_WITH_GIVEN_NAME_ALREADY_EXISTS);
        }

        checkCourseNumberAndFacultyAndSpecialityName(course);

        courseRepository.save(course);
    }

    public List<Course> getAllCourseList() {
        List<Course> result = courseRepository.findAll();
        if (result.size() > 0) {
            return List.copyOf(result);

        } else {
            throw new NoSuchElementException(NO_COURSES_FOUND);
        }
    }

    public Course getCourseById(long id) {
        Optional<Course> courseOptional = courseRepository.findById(id);

        if (courseOptional.isPresent()) {
            return courseOptional.get();
        } else {
            throw new NoSuchElementException(COURSE_WITH_GIVEN_ID_NOT_FOUND);
        }
    }

    public void updateCourse(Course course) {
        Optional<Course> courseOld = courseRepository.findById(course.getId());

        if (courseOld.isPresent()) {
            checkCourseNumberAndFacultyAndSpecialityName(course);

            courseRepository.save(course);
        } else {
            throw new IllegalArgumentException(COURSE_WITH_GIVEN_ID_NOT_FOUND);
        }
    }

    public void deleteCourseById(long id) {
        Optional<Course> courseOptional = courseRepository.findById(id);

        if (courseOptional.isPresent()) {
            courseRepository.deleteById(id);
        } else {
            throw new NoSuchElementException(COURSE_WITH_GIVEN_ID_NOT_FOUND);
        }
    }

    private void checkCourseNumberAndFacultyAndSpecialityName(Course course){
        if (course.getFacultyName().length() < FACULTY_NAME_MIN_LENGTH) {
            throw new IllegalArgumentException(FACULTY_NAME_TOO_SHORT);
        }

        if (course.getSpecialityName().length() < SPECIALITY_NAME_LENGTH) {
            throw new IllegalArgumentException(SPECIALITY_NAME_IS_TOO_SHORT);
        }

        if (course.getCourseNumber() < COURSE_NUMBER_MIN || course.getCourseNumber() > COURSE_NUMBER_MAX) {
            throw new IllegalArgumentException(COURSE_NUMBER_CAN_NOT_BE_MORE_THAN_5_OR_LESS_THAN_1);
        }
    }
}
