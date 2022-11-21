package edu.coderhouse.ecommerce.models.request;

import edu.coderhouse.ecommerce.models.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;

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
