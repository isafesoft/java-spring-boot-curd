package com.mk.rest;

import com.mk.entity.Student;
import com.mk.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("students")
    public List<Student> findAll() {
        return this.studentService.findAll();
    }

    @GetMapping("/students/{studentID}")
    public Student getStudent(@PathVariable int studentID)
    {
        Student student = studentService.findById(studentID);
        if(null == student){
            throw new RuntimeException("Student id not found: " + studentID);
        }
        return student;
    }

    @PostMapping("students")
    public Student addStudent(@RequestBody Student student) {
        student.setId(0);
        this.studentService.save(student);
        return student;
    }

    @PutMapping("/students")
    public Student updateStudent(@RequestBody Student student) {
        this.studentService.save(student);
        return student;
    }


    @DeleteMapping("/students/{studentID}")
    public  String deleteStudent(@PathVariable int studentID)
    {
        Student student = studentService.findById(studentID);
        if(null == student){
            throw new RuntimeException("Student id not found: " + studentID);
        }

        this.studentService.deleteById(studentID);
        return "Deleted student id: " + studentID;
    }
}
