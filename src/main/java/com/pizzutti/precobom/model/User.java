package com.pizzutti.precobom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static com.pizzutti.precobom.model.UserRole.ADMIN;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity(name = "tb_user")
@Schema(description = "An user object contains information about an user registered in the app")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Schema(description = "The user ID", example = "1")
    private Long id;
    @UuidGenerator
    @Schema(description = "The user UUID", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID uuid;
    @Column
    @Schema(description = "The user's email", example = "my@email.com")
    private String email;
    @Column
    @JsonIgnore
    @Schema(hidden = true)
    private String password;
    @Enumerated(STRING)
    @Schema(description = "The user's role in the app", example = "ADMIN")
    private UserRole role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    @JsonIgnore
    @Schema(hidden = true)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return (this.role == ADMIN) ? List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_USER"))
                : List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    @JsonIgnore
    @Schema(hidden = true)
    public String getUsername() {
        return email;
    }

    @Override
    @JsonIgnore
    @Schema(hidden = true)
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    @Schema(hidden = true)
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    @Schema(hidden = true)
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    @Schema(hidden = true)
    public boolean isEnabled() {
        return true;
    }
}
