package ru.javamentor.predProject7.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javamentor.predProject7.exception.DBException;
import ru.javamentor.predProject7.repository.RoleRepository;
import ru.javamentor.predProject7.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Long getRoleIdByName(String name) throws DBException {
        return roleRepository.findRoleByRolename(name).get().getId();
    }

    @Override
    public void addRoles(Long user_id, Long role_id) throws DBException {
        roleRepository.insertRoles(user_id, role_id);
    }
}
