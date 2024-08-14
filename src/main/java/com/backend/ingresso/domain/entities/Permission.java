package com.backend.ingresso.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_permissions", schema = "public")
public class Permission {
    @Id
    @Column(name = "permission_id")
    @JsonProperty("id")
    private UUID Id;
    @Column(name = "visual_name")
    @JsonProperty("visualName")
    private String VisualName;
    @Column(name = "permission_name")
    @JsonProperty("permissionName")
    private String PermissionName;
    @OneToOne(mappedBy = "Permission", cascade = CascadeType.REMOVE)
    private UserPermission userPermission;

    public Permission(UUID id, String visualName, String permissionName) {
        Id = id;
        VisualName = visualName;
        PermissionName = permissionName;
    }

    public Permission() {
    }

    public UUID getId() {
        return Id;
    }

    public String getVisualName() {
        return VisualName;
    }

    public String getPermissionName() {
        return PermissionName;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public void setVisualName(String visualName) {
        VisualName = visualName;
    }

    public void setPermissionName(String permissionName) {
        PermissionName = permissionName;
    }
}
