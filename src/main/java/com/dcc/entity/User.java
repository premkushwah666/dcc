package com.dcc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.*;
import java.util.List;

@Entity
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NonNull String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }

    public @NonNull String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public @NonNull String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
