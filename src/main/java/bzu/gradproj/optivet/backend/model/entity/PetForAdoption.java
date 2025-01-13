package bzu.gradproj.optivet.backend.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "petsForAdoption")
public class PetForAdoption {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birthDate")
    private LocalDate birthDate;

    @Column(nullable = false)
    private String type;


    private String breed;

    @Column(name = "petForAdoptionImageUrl", nullable = false)
    private String petForAdoptionImageUrl;



    @Column(name = "petForAdoptionDescription")
    private String petForAdoptionDescription;
}
