package dev.dariakrylova.university.controller;

import dev.dariakrylova.university.entity.Student;
import dev.dariakrylova.university.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentRestController {

    private final StudentService studentService;

    @Autowired
    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudentJson() {

        return ResponseEntity.ok(studentService.getAllStudentList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") int id) {

        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Student> getStudentById(@PathVariable("name") String name) {
        Long id = studentService.getStudentIdByName(name);

        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        studentService.createStudent(student);

        return new ResponseEntity<>(studentService.getStudentById(student.getId()), HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        studentService.updateStudent(student);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable(name = "id") int id) {
        studentService.deleteStudentById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
