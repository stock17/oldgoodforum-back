package ru.yurima.oldgoodforumback.repositories;

import ru.yurima.oldgoodforumback.entities.User;
import ru.yurima.oldgoodforumback.repositories.Repository;

import java.util.Optional;

public interface UserRepository extends Repository<User> {
    Optional<User> findByLogin(String login);
}
