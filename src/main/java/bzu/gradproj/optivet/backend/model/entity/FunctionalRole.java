package bzu.gradproj.optivet.backend.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "func_roles")
public class FunctionalRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long funcRoleId;

    @Column(nullable = false, unique = true)
    private String roleName;
}
