package bzu.gradproj.optivet.backend.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = true)  // Nullable, can be set later
    private Client client;

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = true)  // Nullable, can be set later
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "vet_id", nullable = false)
    private User vet;

    @Column(name = "appointment_date", nullable = false)
    private LocalDateTime appointmentDate;

    private String status = "AVAILABLE";  // Default status
}
