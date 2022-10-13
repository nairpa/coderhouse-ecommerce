package edu.coderhouse.ecommerce.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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
    @NotBlank(message = "El campo precio es obligatorio")
    @Column(name="precio")
    private BigDecimal precio;
    @NotBlank(message = "El campo descripción es obligatorio")
    @Column(name="descripcion")
    private String descripcion;
    @NotBlank(message = "El campo categoria es obligatorio")
    @Column(name="categoria")
    private String categoria;
}
