package com.backend.ingresso.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_user_permissions", schema = "public")
public class UserPermission {
    @Id
    @Column(name = "user_permission_id")
    @JsonProperty("id")
    private UUID Id;
    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonProperty("user")
    private User User;
    @OneToOne
    @JoinColumn(name = "permission_id")
    @JsonProperty("permission")
    private Permission Permission;

    public UserPermission(UUID id, Permission permission) {
        Id = id;
        Permission = permission;
    }

    public UserPermission(UUID id, User user, Permission permission) {
        Id = id;
        User = user;
        Permission = permission;
    }

    public UserPermission() {
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public User getUser() {
        return User;
    }

    public Permission getPermission() {
        return Permission;
    }
}
