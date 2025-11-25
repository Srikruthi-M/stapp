package com.studentservice.stapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StService {

    @Autowired
    private StRepository stRepository;

    public Student addStudent(Student student) {
        return stRepository.add(student);
    }

    public List<Student> getAllStudents() {
        return stRepository.findAll();
    }

    public Student getStudentById(Integer id) {
        return stRepository.findById(id);
    }

    public Student deleteStudent(Integer id) {
        return stRepository.deleteById(id);
    }

    public Student updateStudent(Student student) {
        return stRepository.update(student);
    }
}
