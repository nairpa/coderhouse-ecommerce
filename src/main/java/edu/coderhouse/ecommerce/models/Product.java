package edu.coderhouse.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="codigo")
    private Long codigo;
    @Column(name="precio")
    private BigDecimal precio;
    @Column(name="descripcion")
    private String descripcion;
    @Column(name="categoria")
    private String categoria;
}
