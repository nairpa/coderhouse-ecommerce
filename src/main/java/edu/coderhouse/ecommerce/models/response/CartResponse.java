package edu.coderhouse.ecommerce.models.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.coderhouse.ecommerce.models.documents.CartItem;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartResponse {
    private List<CartItem> items;
    private LocalDate date;
    private String address;
    private String userId;
}
