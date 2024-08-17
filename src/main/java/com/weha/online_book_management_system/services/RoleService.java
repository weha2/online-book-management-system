package com.weha.online_book_management_system.services;

import com.weha.online_book_management_system.entity.RoleEntity;
import com.weha.online_book_management_system.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<RoleEntity> findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

}
