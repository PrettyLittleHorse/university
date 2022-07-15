package dev.dariakrylova.university.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "course_number", nullable = false)
    private int courseNumber;

    @Column(name = "faculty_name", nullable = false)
    private String facultyName;

    @Column(name = "speciality_name", nullable = false)
    private String specialityName;

    @OneToMany(mappedBy = "course", fetch= FetchType.LAZY)
    @JsonManagedReference
    private Set<Student> students;

    @OneToMany(mappedBy = "course", fetch= FetchType.LAZY)
    @JsonManagedReference
    private Set<Subject> subjects;

    public Course() {
    }

    public Course(Long id, int courseNumber, String facultyName, String specialityName, Set<Student> students, Set<Subject> subjects) {
        this.id = id;
        this.courseNumber = courseNumber;
        this.facultyName = facultyName;
        this.specialityName = specialityName;
        this.students = students;
        this.subjects = subjects;
    }

    public Course(int courseNumber, String facultyName, String specialityName, Set<Student> students, Set<Subject> subjects) {
        this.courseNumber = courseNumber;
        this.facultyName = facultyName;
        this.specialityName = specialityName;
        this.students = students;
        this.subjects = subjects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getSpecialityName() {
        return specialityName;
    }

    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (courseNumber != course.courseNumber) return false;
        if (!id.equals(course.id)) return false;
        if (facultyName != null ? !facultyName.equals(course.facultyName) : course.facultyName != null) return false;
        return specialityName != null ? specialityName.equals(course.specialityName) : course.specialityName == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + courseNumber;
        result = 31 * result + (facultyName != null ? facultyName.hashCode() : 0);
        result = 31 * result + (specialityName != null ? specialityName.hashCode() : 0);
        return result;
    }
}
