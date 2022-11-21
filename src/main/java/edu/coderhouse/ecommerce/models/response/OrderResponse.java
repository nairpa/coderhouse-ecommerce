package edu.coderhouse.ecommerce.models.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.coderhouse.ecommerce.models.documents.CartItem;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponse {
    private Double total;
    private String email;
    private List<CartItem> items;
    private LocalDate purchaseDate;
    private Long orderNumber;
}
