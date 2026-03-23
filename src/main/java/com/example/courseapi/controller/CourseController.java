package com.example.courseapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.courseapi.model.Course;
import com.example.courseapi.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService service;

    // Add
    @PostMapping
    public ResponseEntity<String> addCourse(@RequestBody Course course) {
        service.addCourse(course);
        return new ResponseEntity<>("Course Added Successfully", HttpStatus.CREATED);
    }

    // Get All
    @GetMapping
    public ResponseEntity<List<Course>> getCourses() {
        return new ResponseEntity<>(service.getAllCourses(), HttpStatus.OK);
    }

    // Get by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCourse(@PathVariable int id) {

        Course c = service.getCourseById(id);

        if (c == null) {
            return new ResponseEntity<>("Course Not Found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable int id, @RequestBody Course course) {

        Course updated = service.updateCourse(id, course);

        if (updated == null) {
            return new ResponseEntity<>("Course Not Found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Course Updated Successfully", HttpStatus.OK);
    }

    // Delete (FIXED ✔)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable int id) {

        boolean deleted = service.deleteCourse(id);

        if (!deleted) {
            return new ResponseEntity<>("Course Not Found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Course Deleted Successfully", HttpStatus.OK);
    }

    // Search
    @GetMapping("/search/{title}")
    public ResponseEntity<List<Course>> searchCourse(@PathVariable String title) {

        List<Course> list = service.searchByTitle(title);

        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}