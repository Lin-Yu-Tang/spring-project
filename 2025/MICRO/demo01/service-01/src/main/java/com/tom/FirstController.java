package com.tom;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service1")
public class FirstController {

    @GetMapping
    public List<Student> getStudent() {
        System.out.println("getting students");
        return Arrays.asList(new Student(1,"Ross","Student"),
        new Student(2,"Rachel","Student"));
    }

    @PostMapping
    public Boolean createStudent(@RequestBody Student student) {
        System.out.println("creating Student:"+student);
        return true;
    }

}
