package com.example.demo.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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

    public User(String name, String email, String password)
    {
        this.name = name;
        this.email= email;
        this.password = password;
    }


}
