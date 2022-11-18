package edu.coderhouse.ecommerce.models.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "cart")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    private String id;
    @Transient
    public static final String SEQUENCE_NAME = "orderNumber";
    private Long orderNumber;
    private List<CartItem> items;
    private LocalDate date;
    private String address;
}
