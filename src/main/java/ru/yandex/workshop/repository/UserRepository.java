package ru.yandex.workshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.workshop.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
