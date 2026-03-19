package com.example.courseapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.courseapi.model.Course;
import com.example.courseapi.repository.CourseRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repo;

    // Add
    public Course addCourse(Course c) {
        return repo.save(c);
    }

    // Get All
    public List<Course> getAllCourses() {
        return repo.findAll();
    }

    // Get by ID
    public Course getCourseById(int id) {
        return repo.findById(id).orElse(null);
    }

    // Delete
    public String deleteCourse(int id) {
        repo.deleteById(id);
        return "Deleted Successfully";
    }

    // Update
    public Course updateCourse(int id, Course newCourse) {

        Course old = repo.findById(id).orElse(null);

        if (old != null) {
            old.setTitle(newCourse.getTitle());
            old.setDuration(newCourse.getDuration());
            old.setFee(newCourse.getFee());
            return repo.save(old);
        }

        return null;
    }
}