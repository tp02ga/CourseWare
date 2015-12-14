package com.coderscampus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.coderscampus.domain.Course;

public interface CourseRepository extends JpaRepository<Course, Long>
{
  @Query("select c from Course c where c.user.id = ?#{principal.id} or 1=?#{hasRole('ROLE_ADMIN') ? 1 : 0}")
  public List<Course> findAll();
}
