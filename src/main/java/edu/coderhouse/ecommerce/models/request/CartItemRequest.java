package edu.coderhouse.ecommerce.models.request;
import lombok.*;

import javax.validation.constraints.Min;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemRequest {
    @Min(1)
    private Integer quantity;
}