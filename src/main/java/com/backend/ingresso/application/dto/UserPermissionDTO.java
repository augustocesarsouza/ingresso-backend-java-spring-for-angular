package com.backend.ingresso.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserPermissionDTO {
    private UUID Id;
    private UserDTO userDTO;
    private PermissionDTO permissionDTO;

    public UserPermissionDTO(UUID id, UserDTO userDTOQ, PermissionDTO permissionDTOQ) {
        Id = id;
        this.userDTO = userDTOQ;
        this.permissionDTO = permissionDTOQ;
    }

    public UserPermissionDTO(UUID id, PermissionDTO permissionDTOQ) {
        Id = id;
        this.permissionDTO = permissionDTOQ;
    }

    public UserPermissionDTO() {
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public PermissionDTO getPermissionDTO() {
        return permissionDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public void setPermissionDTO(PermissionDTO permissionDTO) {
        this.permissionDTO = permissionDTO;
    }
}
