package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args ->{
           Student jaype= new Student(
                    "layperson",
                    "jaypersonbabaran@gmail.com",
                    LocalDate.of(2001, JANUARY, 12)
            );

            Student mike= new Student(
                    "mike",
                    "mike@gmail.com",
                    LocalDate.of(2000, JANUARY, 12)

            );

            repository.saveAll(
                    List.of(jaype, mike)
            );
        };
    }
}
