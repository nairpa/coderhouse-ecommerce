package edu.coderhouse.ecommerce.models.documents;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="product")
@Builder
public class Product {
    @Id
    private String id;
    @Transient
    public static final String SEQUENCE_NAME = "code";
    private Long code;
    private Double precio;
    private String descripcion;
    private String category;
}
