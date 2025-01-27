//package bzu.gradproj.optivet.backend.model.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.time.LocalDate;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "pets")
//public class Pet {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false)
//    private String name;
//
//    @Column(nullable = false)
//    private String type;
//
//    private String breed;
//
//    @Column(name = "birth_date")
//    private LocalDate birthDate;
//
//    @Column(name = "medical_history", length = 1000) // Adjust length based on requirements
//    private String medicalHistory;
//
//    @Column(name = "image_url") // New column to store the image URL
//    private String imageUrl;
//
//    //    @OneToOne(mappedBy = "pet",fetch = FetchType.LAZY)
////    @JoinColumn(name = "medicalHistoryy_id",nullable = false)
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "medicalHistory_id", nullable = false)
//    private MedicalHistory medicalHistoryy;
//
////    @ManyToOne
////    @JoinColumn(name = "owner_id", nullable = false)
////    private Client owner; // Use Client explicitly instead of User
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "client_id") // Ensure this column name matches your DB schema
//    private Client owner;
//
//    // New enum attribute for ResidencyType
//    @Enumerated(EnumType.STRING)
//    @Column(name = "residency_type", nullable = true)
//    private ResidencyType residencyType; // Default value is null
//
//    public enum ResidencyType {
//        INPATIENT_CARE,
//        UNCLAIMED,
//        ABANDONED
//    }
//
//    // New Attributes
//    @Column(name = "is_deleted", nullable = false)
//    private boolean deleted = false; // Default value is false
//
//    @Column(name = "manual_id", unique = true)
//    private String manualId; // Optional field for manual ID
//
//    @Column(name = "gender")
//    private String gender; // Gender (e.g., Male, Female, etc.)
//
////    public boolean isDeleted() {
////        return isDeleted;
////    }
////
////    public void setIsDeleted(boolean isDeleted) {
////        this.isDeleted = isDeleted;
////    }
//
//}









package bzu.gradproj.optivet.backend.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    private String breed;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "medical_history", length = 1000) // Adjust length based on requirements
    private String medicalHistory;

    @Column(name = "image_url") // New column to store the image URL
    private String imageUrl;

    //    @OneToOne(mappedBy = "pet",fetch = FetchType.LAZY)
//    @JoinColumn(name = "medicalHistoryy_id",nullable = false)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "medicalHistory_id", nullable = false)
    private MedicalHistory medicalHistoryy;

//    @ManyToOne
//    @JoinColumn(name = "owner_id", nullable = false)
//    private Client owner; // Use Client explicitly instead of User

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id") // Ensure this column name matches your DB schema
    private Client owner;

    // New enum attribute for ResidencyType
    @Enumerated(EnumType.STRING)
    @Column(name = "residency_type", nullable = true)
    private ResidencyType residencyType; // Default value is null

    public enum ResidencyType {
        INPATIENT_CARE,
        UNCLAIMED,
        ABANDONED
    }

    // New Attributes
    @Column(name = "is_deleted", nullable = false)
    private boolean deleted = false; // Default value is false

    @Column(name = "manual_id", unique = true)
    private String manualId; // Optional field for manual ID

    @Column(name = "gender")
    private String gender; // Gender (e.g., Male, Female, etc.)

//    public boolean isDeleted() {
//        return isDeleted;
//    }
//
//    public void setIsDeleted(boolean isDeleted) {
//        this.isDeleted = isDeleted;
//    }

}

