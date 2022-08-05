package dev.dariakrylova.university.controller;


import dev.dariakrylova.university.entity.Course;
import dev.dariakrylova.university.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/course")
public class CourseRestController {

    private final CourseService courseService;

    @Autowired
    public CourseRestController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Course>> getAllCourseJson() {

        return ResponseEntity.ok(courseService.getAllCourseList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable("id") int id) {

        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        courseService.createCourse(course);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Course> updateCourse(@RequestBody Course course) {
        courseService.updateCourse(course);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable(name = "id") int id) {
        courseService.deleteCourseById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
