package edu.coderhouse.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.UUID;


@Document(collection="usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private ObjectId _id;
    @NotBlank(message = "El campo username es obligatorio")
    private String username;
    @NotBlank(message = "El campo name es obligatorio")
    private String name;
    private String phoneNumber;
    @NotBlank(message = "El campo password es obligatorio")
    private String password;
    @NotBlank(message = "El campo email es obligatorio")
    private String email;
}
