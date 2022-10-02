package edu.coderhouse.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


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
    @Column(name="username")
    private String username;
    @Column(name="name")
    private String name;
    @Column(name="phoneNumber")
    private String phoneNumber;
    @Column(name="email")
    private String email;
}
