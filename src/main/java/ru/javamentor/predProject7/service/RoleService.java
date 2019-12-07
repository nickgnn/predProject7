package ru.javamentor.predProject7.service;

import ru.javamentor.predProject7.exception.DBException;

public interface RoleService {
    Long getRoleIdByName(String name) throws DBException;
    void addRoles(Long user_id, Long role_id) throws DBException;
}