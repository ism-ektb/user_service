package ru.yandex.workshop.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.workshop.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {


}
