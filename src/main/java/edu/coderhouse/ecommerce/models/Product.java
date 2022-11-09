package edu.coderhouse.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="product")
public class Product {
    @Id
    private String id;
    @NotNull(message = "El campo código es obligatorio")
    private String codigo;
    @NotNull(message = "El campo precio es obligatorio")
    private BigDecimal precio;
    @NotNull(message = "El campo descripción es obligatorio")
    @NotBlank(message = "El campo descripción es obligatorio")
    private String descripcion;
    @NotNull(message = "El campo categoria es obligatorio")
    @NotBlank(message = "El campo categoria es obligatorio")
    private String categoria;
}
