package com.example.demo.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudent(){
        return studentService.getStudent();
    }

    @GetMapping(path="{studentId}")
    public Student getStudent(@PathVariable("studentId") Long studentId){
        return studentService.getStudent(studentId);
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path="{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
       studentService.deleteStudent(studentId);
    }

    @PutMapping(path="{studentId}")
    public void updateStudent(
            @PathVariable("studentId")Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false)String email)
    {
        studentService.updateStudent(studentId, name, email);

    }
}
