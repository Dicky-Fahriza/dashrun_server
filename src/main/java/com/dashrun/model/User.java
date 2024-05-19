package com.dashrun.model;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")

public class User {
    @Id
    private String username;
    private String name;
    private String password;
    private String role;
    private String token;
    private String status;

    @Column(name = "token_expired_at")
    private Long tokenExpiredAt;
    
    @CreationTimestamp  
    @Column(name="created_at")
    private Instant created_at;

    @UpdateTimestamp
    @Column(name="updated_at")
    private Instant updated_at;
}
