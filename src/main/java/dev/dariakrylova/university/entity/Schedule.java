package dev.dariakrylova.university.entity;


import java.util.Objects;

public class Schedule {

    private final String today;

    private final String weekDay;
    private final String subjectName;
    private final String startDate;
    private final String stopDate;

    public Schedule(String today, String weekDay, String subjectName, String startDate, String stopDate) {
        this.today = today;
        this.weekDay = weekDay;
        this.subjectName = subjectName;
        this.startDate = startDate;
        this.stopDate = stopDate;
    }

    public String getToday() {
        return today;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getStopDate() {
        return stopDate;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "today='" + today + '\'' +
                ", weekDay='" + weekDay + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", stopDate='" + stopDate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Schedule schedule = (Schedule) o;

        if (!Objects.equals(today, schedule.today)) return false;
        if (!Objects.equals(subjectName, schedule.subjectName)) return false;
        if (!Objects.equals(startDate, schedule.startDate)) return false;
        return Objects.equals(stopDate, schedule.stopDate);
    }

    @Override
    public int hashCode() {
        int result = today != null ? today.hashCode() : 0;
        result = 31 * result + (subjectName != null ? subjectName.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (stopDate != null ? stopDate.hashCode() : 0);
        return result;
    }
}
