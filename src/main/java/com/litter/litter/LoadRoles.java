package com.litter.litter;

import com.litter.litter.model.ERole;
import com.litter.litter.model.Role;
import com.litter.litter.service.role.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LoadRoles implements CommandLineRunner {
    @Autowired
    RoleServiceImpl roleService;

    @Override
    public void run(String... args) throws Exception {
        if (!roleService.existsByName(ERole.ROLE_USER)) roleService.save(new Role(ERole.ROLE_USER));

        if (!roleService.existsByName(ERole.ROLE_MODERATOR)) roleService.save(new Role(ERole.ROLE_MODERATOR));

        if (!roleService.existsByName(ERole.ROLE_ADMIN)) roleService.save(new Role(ERole.ROLE_ADMIN));
    }
}
