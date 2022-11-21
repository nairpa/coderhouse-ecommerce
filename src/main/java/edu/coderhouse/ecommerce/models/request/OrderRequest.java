package edu.coderhouse.ecommerce.models.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
    @NotNull
    private String userId;
}
