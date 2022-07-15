package dev.dariakrylova.university.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "stop_date")
    private LocalDate stopDate;

    @Column(name = "week_day")
    private String weekDay;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id")
    @JsonBackReference
    private Course course;

    public Subject() {
    }

    public Subject(Long id, String subjectName, LocalDate startDate, LocalDate stopDate, String weekDay, Course course) {
        this.id = id;
        this.subjectName = subjectName;
        this.startDate = startDate;
        this.stopDate = stopDate;
        this.weekDay = weekDay;
        this.course = course;
    }

    public Subject(String subjectName, LocalDate startDate, LocalDate stopDate, String weekDay, Course course) {
        this.subjectName = subjectName;
        this.startDate = startDate;
        this.stopDate = stopDate;
        this.weekDay = weekDay;
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getStopDate() {
        return stopDate;
    }

    public void setStopDate(LocalDate stopDate) {
        this.stopDate = stopDate;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
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
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        if (!id.equals(subject.id)) return false;
        if (!subjectName.equals(subject.subjectName)) return false;
        if (startDate != null ? !startDate.equals(subject.startDate) : subject.startDate != null) return false;
        if (stopDate != null ? !stopDate.equals(subject.stopDate) : subject.stopDate != null) return false;
        if (weekDay != null ? !weekDay.equals(subject.weekDay) : subject.weekDay != null) return false;
        return course != null ? course.equals(subject.course) : subject.course == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + subjectName.hashCode();
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (stopDate != null ? stopDate.hashCode() : 0);
        result = 31 * result + (weekDay != null ? weekDay.hashCode() : 0);
        return result;
    }
}
