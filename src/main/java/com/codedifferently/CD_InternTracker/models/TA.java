package com.codedifferently.CD_InternTracker.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "app_user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

 API-endpoint-to-fetch-TA-Weekly-schedules
    private String name;

dev
    private String email;
    private String name;
    private String subject;
    private String password;
    private String phoneNumber;
    private boolean isAdmin;
    private boolean isTA;

 API-endpoint-to-fetch-TA-Weekly-schedules
    // One-to-many relationship with WeeklySchedule
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ta_id")  // Foreign key in WeeklySchedule table
    private List<WeeklySchedule> weeklySchedule;

    // Constructor
    public TA(Long id, String name, String email, String password, String phoneNumber, boolean isAdmin, boolean isTA) {
        this.id = id;
        this.name = name;

    public TA(String email, String name, String subject, String password, String phoneNumber, boolean isAdmin, boolean isTA) {
dev
        this.email = email;
        this.name = name;
        this.subject = subject;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isAdmin = isAdmin;
        this.isTA = isTA;
    }

 API-endpoint-to-fetch-TA-Weekly-schedules

    // Getter and Setter Methods for each property

 dev
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isTA() {
        return isTA;
    }

    public void setTA(boolean TA) {
        isTA = TA;
    }
 API-endpoint-to-fetch-TA-Weekly-schedules

}

}
dev
