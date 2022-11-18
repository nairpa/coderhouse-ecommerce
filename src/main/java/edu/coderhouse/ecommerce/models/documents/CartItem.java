package edu.coderhouse.ecommerce.models.documents;

import lombok.*;

import javax.validation.constraints.Min;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Integer quantity;
    private Long code;
}
