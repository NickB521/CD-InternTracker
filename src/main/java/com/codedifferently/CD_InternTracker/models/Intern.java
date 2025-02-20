package com.codedifferently.CD_InternTracker.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Entity
@Access(AccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Intern {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String level;

    @ElementCollection
    @CollectionTable(name = "intern_schedule", joinColumns = @JoinColumn(name = "intern_id"))
    private List<DailySchedule> weeklySchedule;

    private String internNotes;

    @ElementCollection
    @CollectionTable(name = "intern_attendance", joinColumns = @JoinColumn(name = "intern_id"))
    private List<String> attendance;

    public Intern(String name, String email, Long id) {
        this.name = name;
        this.email = email;
        this.id = id;
    }
}