package aleksey.model;

import java.time.LocalDate;
import java.util.Objects;

public class Tracking {
    private String personEmail;
    private String habitName;
    private LocalDate date;
    private Boolean isMark;

    public Tracking(String habitName, String personEmail) {
        this.habitName = habitName;
        this.personEmail = personEmail;
        this.date = LocalDate.now();
        this.isMark = true;
    }

    public Tracking(LocalDate date, String habitName, Boolean isMark, String personEmail) {
        this.date = date;
        this.habitName = habitName;
        this.isMark = isMark;
        this.personEmail = personEmail;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getHabitName() {
        return habitName;
    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }

    public Boolean getMark() {
        return isMark;
    }

    public void setMark(Boolean mark) {
        isMark = mark;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Tracking tracking)) return false;
        return Objects.equals(getPersonEmail(), tracking.getPersonEmail()) && Objects.equals(getHabitName(), tracking.getHabitName()) && Objects.equals(getDate(), tracking.getDate()) && Objects.equals(isMark, tracking.isMark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPersonEmail(), getHabitName(), getDate(), isMark);
    }

    @Override
    public String toString() {
        return "Tracking{" +
                "date=" + date +
                ", personEmail='" + personEmail + '\'' +
                ", habitName='" + habitName + '\'' +
                ", isMark=" + isMark +
                '}';
    }
}