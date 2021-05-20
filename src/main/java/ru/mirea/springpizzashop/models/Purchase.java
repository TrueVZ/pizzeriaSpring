package ru.mirea.springpizzashop.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "cart")
@Data
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "user_id")
    Long userId;
    @Column(name = "product_id")
    Long productId;
    @Column(name = "product_count")
    int productCount;


}
