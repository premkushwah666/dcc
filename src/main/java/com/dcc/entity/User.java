package com.dcc.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import com.dcc.Enumiration.Role;


@Entity
@Table(name = "users")//Document(~table)
@Data
@Builder
@AllArgsConstructor   //for builder
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull
    private String userName;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    
    private boolean isActive = true;
    //private List<String> roles;

}
