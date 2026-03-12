package com.example.courseapi.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.courseapi.model.Course;

@Service
public class CourseService {

    private List<Course> courseList = new ArrayList<>();

    // Add course
    public Course addCourse(Course course) {
        courseList.add(course);
        return course;
    }

    // Get all courses
    public List<Course> getAllCourses() {
        return courseList;
    }

    // Get course by id
    public Course getCourseById(int id) {
        return courseList.stream()
                .filter(c -> c.getCourseId() == id)
                .findFirst()
                .orElse(null);
    }

    // Update course
    public Course updateCourse(int id, Course course) {
        for (Course c : courseList) {
            if (c.getCourseId() == id) {
                c.setTitle(course.getTitle());
                c.setDuration(course.getDuration());
                c.setFee(course.getFee());
                return c;
            }
        }
        return null;
    }

    // Delete course
    public boolean deleteCourse(int id) {
        return courseList.removeIf(c -> c.getCourseId() == id);
    }

    // Search by title
    public List<Course> searchByTitle(String title) {
        List<Course> result = new ArrayList<>();
        for (Course c : courseList) {
            if (c.getTitle().equalsIgnoreCase(title)) {
                result.add(c);
            }
        }
        return result;
    }
}