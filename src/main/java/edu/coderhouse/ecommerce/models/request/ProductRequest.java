package edu.coderhouse.ecommerce.models.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class ProductRequest {
    @NotNull(message = "El campo precio es obligatorio")
    private BigDecimal precio;
    @NotNull(message = "El campo descripción es obligatorio")
    @NotBlank(message = "El campo descripción es obligatorio")
    private String descripcion;
    @NotNull(message = "El campo categoria es obligatorio")
    @NotBlank(message = "El campo categoria es obligatorio")
    private String category;
    private int cantidad;
}
