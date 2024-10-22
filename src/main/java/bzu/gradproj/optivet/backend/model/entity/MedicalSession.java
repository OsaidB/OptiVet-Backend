package bzu.gradproj.optivet.backend.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "medical_sessions")
public class MedicalSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @Column(nullable = false)
    @Column(length = 1000)
    private String diagnosis;

    @Column(nullable = false)
    private String treatment;

    @Column(name = "session_date", nullable = false)
    private LocalDateTime sessionDate;

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet; // Link the session to a specific pet

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Client owner;

    @ManyToOne
    @JoinColumn(name = "vet_id", nullable = false)
    private User veterinarian; // Link the session to a specific veterinarian

    // Medical Details
    @Column(length = 1000)
    private String symptoms;

    @Column(length = 1000)
    private String treatmentPlan;

    @Column(length = 1000)
    private String medicationsPrescribed;

    // Vital Signs
    private Double weight;
    private Double temperature;
    private Integer heartRate;

    // Session Notes
    @Column(length = 2000)
    private String veterinarianNotes;

    // Lab Testing
    @Column(length = 1000)
    private String testsOrdered;

    @Column(name = "test_results_image_url")
    private String testResultsImageUrl; // Store image URL for test results

    // Follow-up Actions
    private LocalDate nextAppointmentDate;

    @Column(length = 1000)
    private String postTreatmentInstructions;
}
