package edu.coderhouse.ecommerce.models.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotNull
    private String name;
    @NotNull
    private String username;
    private String phoneNumber;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;
}
