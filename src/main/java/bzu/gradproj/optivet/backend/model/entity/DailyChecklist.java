package bzu.gradproj.optivet.backend.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "daily_checklists")
public class DailyChecklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    // Health Checks
    private Boolean eatingWell;
    private Boolean drinkingWater;
    private Boolean activeBehavior;
    private Boolean normalVitalSigns;

    // Observations
    @Column(length = 1000)
    private String healthObservations;
    @Column(length = 500)
    private String weightChange;
    @Column(length = 1000)
    private String injuriesOrWounds;

    // Maintenance
    private Boolean feedingCompleted;
    private Boolean cleanedLivingSpace;

    // Poop Check
    private Boolean poopNormal;
    @Column(length = 1000)
    private String poopNotes;

    // Critical Notes
    private Boolean criticalIssueFlag;
    @Column(length = 2000)
    private String criticalNotes;
}
