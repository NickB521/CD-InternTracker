package com.codedifferently.CD_InternTracker.models;


import jakarta.persistence.Embeddable;
import org.springframework.lang.NonNull;

@Entity
@Access(AccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailySchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String weekDay;

    @NonNull
    private String startTime;

    @NonNull
    private String endTime;

    // Default constructor
    public DailySchedule() {
    }


    // Constructor with parameters
    public DailySchedule(String weekDay, String startTime, String endTime) {
        this.weekDay = weekDay;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters and setters

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    // Override toString method for better representation
    @Override
    public String toString() {
        return "DailySchedule{" +
                "weekDay='" + weekDay + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}