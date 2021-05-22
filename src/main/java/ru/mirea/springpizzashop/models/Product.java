package ru.mirea.springpizzashop.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    @Column(name = "product_name")
    private String name;
    @Column(name = "price")
    private int price;
    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String image_url;

    @Lob
    @Column(name = "picture")
    private byte[] picture;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_type_id")
    private ProductType productType;


}
