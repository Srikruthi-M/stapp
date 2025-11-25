package com.studentservice.stapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("stapp")
public class StController {

    @Autowired
    private StService stService;

    @PostMapping("add")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return new ResponseEntity<>(stService.addStudent(student), HttpStatus.CREATED);
    }

    @GetMapping("all")
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<>(stService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("find")
    public ResponseEntity<Student> getStudentById(@RequestParam Integer id) {
        return new ResponseEntity<>(stService.getStudentById(id), HttpStatus.OK);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Student> deleteStudent(@RequestParam Integer id) {
        return new ResponseEntity<>(stService.deleteStudent(id), HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        return new ResponseEntity<>(stService.updateStudent(student), HttpStatus.OK);
    }
}
