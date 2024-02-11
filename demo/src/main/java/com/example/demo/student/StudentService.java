package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
        System.out.println();
    }
    public List<Student> getStudent(){
        return studentRepository.findAll();
    }

    public Student getStudent(Long id){   
        Student student = studentRepository.findById(id).orElseThrow(()-> new IllegalStateException("Student with id does not exist"));
        return student;
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional= studentRepository.
                findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        studentRepository.findById(studentId);
        boolean exists =studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("Student with id " + studentId+ " does not exist");
        }

        studentRepository.deleteById(studentId);
    }

    @Transactional
    public  void updateStudent(Long studentId, String name, String email){
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new IllegalStateException("Student with id "+ studentId +" does not exist"));

        if (name !=null && !name.isEmpty() && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        if (email !=null && !email.isEmpty() && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional= studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }

            student.setEmail(email);
        }
    }
}
