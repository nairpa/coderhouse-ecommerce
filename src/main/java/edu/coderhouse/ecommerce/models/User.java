package edu.coderhouse.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Entity
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    @NotBlank(message = "El campo username es obligatorio")
    @Column(name="username")
    private String username;
    @NotBlank(message = "El campo name es obligatorio")
    @Column(name="name")
    private String name;
    @Column(name="phoneNumber")
    private String phoneNumber;
    @NotBlank(message = "El campo email es obligatorio")
    @Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", message = "Debe ser un email válido")
    @Column(name="email")
    private String email;
}
