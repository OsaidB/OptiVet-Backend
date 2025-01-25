//package bzu.gradproj.optivet.backend.model.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.List;
//
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "medical_history")
//public class MedicalHistory {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//
//    @Column(name = "dietary_preferences", nullable = true)
//    private String dietaryPreferences;
//
//
//    //overall notes written by the client for describing anything for the doctor about the pet
//    @Column(name = "notes", nullable = true)
//    private String notes;
//
//
//    @ElementCollection
//    @CollectionTable(name = "medical_history_images", joinColumns = @JoinColumn(name = "medical_history_id"))
//    @Column(name = "url")
//    private List<String> medicalHistoryImageUrls;
//
//    //mappedBy
//
////    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
////    private Pet pet;
//
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "medical_history_id", referencedColumnName = "id")
//    private List<ChronicConditions> chronicConditions;
//
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "medical_history_id", referencedColumnName = "id")
//    private List<Allergies> allergies;
//
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "medical_history_id", referencedColumnName = "id")
//    private List<Vaccinations> vaccinations;
//
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "medical_history_id", referencedColumnName = "id")
//    private List<Surgeories> surgeories;
//
//
////    @OneToOne(fetch = FetchType.LAZY)
////    private Pet pet;
//}















package bzu.gradproj.optivet.backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "medical_history")
public class MedicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "dietary_preferences", nullable = true)
    private String dietaryPreferences;


    //overall notes written by the client for describing anything for the doctor about the pet
    @Column(name = "notes", nullable = true)
    private String notes;


    @ElementCollection
    @CollectionTable(name = "medical_history_images", joinColumns = @JoinColumn(name = "medical_history_id"))
    @Column(name = "url")
    private List<String> medicalHistoryImageUrls;

    //mappedBy

//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Pet pet;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "medical_history_id", referencedColumnName = "id")
    private List<ChronicConditions> chronicConditions;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "medical_history_id", referencedColumnName = "id")
    private List<Allergies> allergies;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "medical_history_id", referencedColumnName = "id")
    private List<Vaccinations> vaccinations;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "medical_history_id", referencedColumnName = "id")
    private List<Surgeories> surgeories;


//    @OneToOne(fetch = FetchType.LAZY)
//    private Pet pet;
}
