package edu.coderhouse.ecommerce.models.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductResponse {
    private Long code;
    private BigDecimal precio;
    private int cantidad;
    private String descripcion;
    private String category;
}
