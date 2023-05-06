package com.product.pocproducts.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private static final long serialVersionUID = -2899467848569731944L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String validity;
    @Column(nullable = false)
    private Integer qtt;
    @Column(nullable = false)
    private BigDecimal value;
}
