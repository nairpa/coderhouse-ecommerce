package edu.coderhouse.ecommerce.models.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartRequest {
    @NotBlank
    @NotNull
    private String address;
    private String userId;
}
