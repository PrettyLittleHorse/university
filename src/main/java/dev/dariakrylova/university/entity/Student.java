package dev.dariakrylova.university.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "group_number")
    private int groupNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id")
    @JsonBackReference
    private Course course;

    public Student() {
    }

    public Student(Long id, String studentName, int groupNumber, Course course) {
        this.id = id;
        this.studentName = studentName;
        this.groupNumber = groupNumber;
        this.course = course;
    }

    public Student(String studentName, int groupNumber, Course course) {
        this.studentName = studentName;
        this.groupNumber = groupNumber;
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;

        Student student = (Student) o;

        if (getGroupNumber() != student.getGroupNumber()) return false;
        if (!getId().equals(student.getId())) return false;
        if (!getStudentName().equals(student.getStudentName())) return false;
        return getCourse() != null ? getCourse().equals(student.getCourse()) : student.getCourse() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getStudentName().hashCode();
        result = 31 * result + getGroupNumber();
        result = 31 * result + (getCourse() != null ? getCourse().hashCode() : 0);
        return result;
    }
}
