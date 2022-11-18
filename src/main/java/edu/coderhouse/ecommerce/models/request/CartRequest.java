package edu.coderhouse.ecommerce.models.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class CartRequest {
    @NotBlank
    @NotNull
    private String address;
}
