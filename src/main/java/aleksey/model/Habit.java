package aleksey.model;

import java.time.LocalDate;
import java.util.Objects;

public class Habit {
    private String name;
    private String description;
    private LocalDate createDate;
    private String frequency;

    public Habit(LocalDate createDate, String description, String frequency, String name) {
        this.createDate = createDate;
        this.description = description;
        this.frequency = frequency;
        this.name = name;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Habit habit)) return false;
        return Objects.equals(getName(), habit.getName()) && Objects.equals(getDescription(), habit.getDescription()) && Objects.equals(getCreateDate(), habit.getCreateDate()) && Objects.equals(getFrequency(), habit.getFrequency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription(), getCreateDate(), getFrequency());
    }

    @Override
    public String toString() {
        return "Habit{" +
                "createDate=" + createDate +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", frequency='" + frequency + '\'' +
                '}';
    }
}