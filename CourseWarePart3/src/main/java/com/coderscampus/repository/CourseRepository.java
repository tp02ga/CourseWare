package com.coderscampus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.domain.Course;

public interface CourseRepository extends JpaRepository<Course, Long>
{

}
