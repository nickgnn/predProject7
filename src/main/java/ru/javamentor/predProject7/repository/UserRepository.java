package ru.javamentor.predProject7.repository;

import org.springframework.data.repository.CrudRepository;
import ru.javamentor.predProject7.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
