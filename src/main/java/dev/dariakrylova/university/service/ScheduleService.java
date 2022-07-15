package dev.dariakrylova.university.service;

import dev.dariakrylova.university.entity.Schedule;
import dev.dariakrylova.university.entity.Student;
import dev.dariakrylova.university.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {
    private final StudentService studentService;
    private final SubjectService subjectService;

    @Autowired
    public ScheduleService(StudentService studentService, SubjectService subjectService) {
        this.studentService = studentService;
        this.subjectService = subjectService;
    }

    public List<Schedule> getSchedule(Long id, LocalDate date) {
        Student student = studentService.getStudentById(id);
        List<Subject> subjects = subjectService.getAllAvailableSubjectsListByDate(date);
        List<Schedule> schedules = new ArrayList<>();

        for (Subject subject : subjects) {
            if (subject.getCourse().getCourseNumber() == student.getCourse().getCourseNumber()
                    && subject.getCourse().getSpecialityName().equals(student.getCourse().getSpecialityName())) {

                if (subject.getWeekDay().toUpperCase().equals(date.getDayOfWeek().toString())) {

                    schedules.add(new Schedule(date.toString(), subject.getWeekDay(), subject.getSubjectName(), subject.getStartDate().toString(), subject.getStopDate().toString()));
                }
            }
        }

        return schedules;
    }

}
