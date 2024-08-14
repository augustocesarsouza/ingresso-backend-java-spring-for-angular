package com.backend.ingresso.application.services;

import com.backend.ingresso.application.dto.PermissionDTO;
import com.backend.ingresso.application.mappings.MappingClassInterface.IMovieMapper;
import com.backend.ingresso.application.mappings.MappingClassInterface.IPermissionMapper;
import com.backend.ingresso.application.services.interfaces.IPermissionService;
import com.backend.ingresso.domain.entities.Permission;
import com.backend.ingresso.domain.repositories.IPermissionRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PermissionService implements IPermissionService {
    private final IPermissionRepository permissionRepository;
    private final IPermissionMapper permissionMapper;

    public PermissionService(IPermissionRepository permissionRepository, IMovieMapper movieMapper, IPermissionMapper permissionMapper) {
        this.permissionRepository = permissionRepository;
        this.permissionMapper = permissionMapper;
    }

    @Override
    public ResultService<PermissionDTO> getIdByVisualName(String visualName) {
        try {
            PermissionDTO permission = permissionRepository.getIdByVisualName(visualName);

            if(permission == null){
                return ResultService.Fail("error null when get the permission");
            }

            return ResultService.Ok(permission);

        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<Permission> findById(UUID permissionId) {
        try {
            Permission permission = permissionRepository.findById(permissionId);

            if(permission == null){
                return ResultService.Fail("error null when get the permission");
            }

            return ResultService.Ok(permission);

        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }
}
