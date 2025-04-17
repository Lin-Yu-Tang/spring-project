package com.tom;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/second")
public class SecondController {

	@GetMapping
    public List<Course> getCompanies() {
        System.out.println("getting Companies");
        return Arrays.asList(new Course(1, "economics", 3),
        new Course(2, "bible", 2));
    }

    @PostMapping
    public Boolean createCompany(@RequestBody Course course) {
        System.out.println("creating Company:"+course);
        return true;
    }

    
}
