package com.example.courseapi.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.courseapi.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    List<Course> findByTitleContainingIgnoreCase(String title);
}