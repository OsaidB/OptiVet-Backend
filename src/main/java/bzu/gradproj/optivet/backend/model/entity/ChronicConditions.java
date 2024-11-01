package bzu.gradproj.optivet.backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chronic_conditions")
public class ChronicConditions {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;


        @Column( name="chronic_condition",nullable = false)
        private String chronicCondition;

        @ManyToOne
        @JoinColumn(name = "medical_history_id", nullable = false)
        private MedicalHistory medicalHistory;


}
