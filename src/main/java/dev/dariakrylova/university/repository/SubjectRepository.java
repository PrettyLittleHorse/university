package dev.dariakrylova.university.repository;

import dev.dariakrylova.university.entity.Course;
import dev.dariakrylova.university.entity.Student;
import dev.dariakrylova.university.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;


@RepositoryRestResource(collectionResourceRel = "subjects", path = "subjects")
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Optional<Subject> findSubjectBySubjectName(@Param("name") String name);
}
