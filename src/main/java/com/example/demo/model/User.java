package com.example.demo.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name= "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false,unique = true)
    private String email;
    @Column(name = "password", nullable=false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public enum Role
    {
        JOBSEEKER, EMPLOYER
    }


}
