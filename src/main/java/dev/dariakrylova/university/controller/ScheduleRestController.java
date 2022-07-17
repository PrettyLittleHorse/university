package dev.dariakrylova.university.controller;

import dev.dariakrylova.university.entity.Schedule;
import dev.dariakrylova.university.service.ScheduleService;
import dev.dariakrylova.university.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleRestController {

    private final ScheduleService scheduleService;
    private final StudentService studentService;

    @Autowired
    public ScheduleRestController(ScheduleService scheduleService, StudentService studentService) {

        this.scheduleService = scheduleService;
        this.studentService = studentService;
    }

    @GetMapping("/id{studentId}/date{date}")
    public ResponseEntity<List<Schedule>> getScheduleJson(@PathVariable Long studentId, @PathVariable String date) {

        return ResponseEntity.ok(scheduleService.getSchedule(studentId, LocalDate.parse(date)));
    }

    @GetMapping("/{studentName}/{date}")
    public ResponseEntity<List<Schedule>> getScheduleJson(@PathVariable String studentName, @PathVariable String date) {

        return ResponseEntity.ok(
                scheduleService.getSchedule(
                        studentService.getStudentIdByName(studentName), LocalDate.parse(date))
        );
    }

}
