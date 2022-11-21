package edu.coderhouse.ecommerce.models.documents;

import edu.coderhouse.ecommerce.models.enums.RoleEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document(collection="role")
public class Role {
    @Id
    String id;
    RoleEnum name;
}
