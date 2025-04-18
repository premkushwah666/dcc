package com.dcc.entity;


import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;

@Table(name = "users")//Document(~table)
@Data
@Builder
@AllArgsConstructor   //for builder
@NoArgsConstructor
public class User {
    @Id
    private Integer id;
    @NonNull
    private String userName;
    @NonNull
    private String email;
    @NonNull
    private String password;
    private List<String> roles;
}
