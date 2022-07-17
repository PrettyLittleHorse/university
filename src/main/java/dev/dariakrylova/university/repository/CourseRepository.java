package dev.dariakrylova.university.repository;

import dev.dariakrylova.university.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;


@RepositoryRestResource(collectionResourceRel = "courses", path = "courses")
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findCourseByCourseNumber(@Param("courseNumber") int courseNumber);
}
