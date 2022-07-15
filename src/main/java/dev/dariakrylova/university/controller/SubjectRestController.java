package dev.dariakrylova.university.controller;

import dev.dariakrylova.university.entity.Subject;
import dev.dariakrylova.university.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectRestController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectRestController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Subject>> getAllSubjectJson() {

        return ResponseEntity.ok(subjectService.getAllSubjectList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable("id") int id) {

        return ResponseEntity.ok(subjectService.getSubjectById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Subject> addSubject(@RequestBody Subject subject) {
        subjectService.createSubject(subject);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Subject> updateSubject(@RequestBody Subject subject) {
        subjectService.updateSubject(subject);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable(name = "id") int id) {
        subjectService.deleteSubjectById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
