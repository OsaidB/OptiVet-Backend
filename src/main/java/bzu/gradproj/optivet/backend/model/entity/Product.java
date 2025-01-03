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
@Table(name = "products")
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name", length = 100, nullable = false)
    private String name;


    @Column(name = "productImageUrl", nullable = false)
    private String productImageUrl;




    @Column(name = "price")
    private double price;


    @Column(name = "productCategory", nullable = false)
    private String productCategory;
}
