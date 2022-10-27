package edu.coderhouse.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Document(collection = "producto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private ObjectId _id;
    @NotNull(message = "El campo precio es obligatorio")
    @NotNull(message = "El campo codigo es obligatorio")
    private int codigo;
    private BigDecimal precio;
    @NotNull(message = "El campo descripción es obligatorio")
    @NotBlank(message = "El campo descripción es obligatorio")
    private String descripcion;
    @NotNull(message = "El campo categoria es obligatorio")
    @NotBlank(message = "El campo categoria es obligatorio")
    private String categoria;
}
