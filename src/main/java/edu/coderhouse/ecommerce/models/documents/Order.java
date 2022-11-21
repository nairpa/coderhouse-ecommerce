package edu.coderhouse.ecommerce.models.documents;

import edu.coderhouse.ecommerce.models.enums.StateEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection="order")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Transient
    public static final String SEQUENCE_NAME = "orderNumber";
    @Id
    private Long orderNumber;
    private String userId;
    private List<CartItem> items;
    private Double total;
    private LocalDate purchaseDate;
    private StateEnum state;
    private String email;
}
