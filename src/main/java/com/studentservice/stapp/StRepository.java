package com.studentservice.stapp;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StRepository {

    private HashMap<Integer, Student> studentMap = new HashMap<>();

    public Student add(Student student) {
        studentMap.put(student.getId(), student);
        return student;
    }

    public List<Student> findAll() {
        return new ArrayList<>(studentMap.values());
    }

    public Student findById(Integer id) {
        return studentMap.get(id);
    }

    public Student deleteById(Integer id) {
        return studentMap.remove(id);
    }

    public Student update(Student student) {
        studentMap.put(student.getId(), student);
        return student;
    }
}
