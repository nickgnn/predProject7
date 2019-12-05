package ru.javamentor.predProject7.repository;

import org.springframework.data.repository.CrudRepository;
import ru.javamentor.predProject7.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
