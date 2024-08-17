package com.weha.online_book_management_system.seeder;

import com.weha.online_book_management_system.constans.RoleName;
import com.weha.online_book_management_system.entity.RoleEntity;
import com.weha.online_book_management_system.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            List<RoleEntity> roles = new ArrayList<>();
            roles.add(new RoleEntity(RoleName.USER.name()));
            roles.add(new RoleEntity(RoleName.ADMIN.name()));
            roleRepository.saveAll(roles);
        }
    }
}
