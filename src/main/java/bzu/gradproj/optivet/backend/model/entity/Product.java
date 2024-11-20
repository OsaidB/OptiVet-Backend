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
@Table(name = "products")
public class Product {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name", nullable = false)
    private String name;


    @Column(name = "productCategory", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;


    @Column(name = "quantity")
    private int quantity;



    public enum ProductCategory {
        FOOD, TREATS, TOYS, COLLARS;
    }
}
