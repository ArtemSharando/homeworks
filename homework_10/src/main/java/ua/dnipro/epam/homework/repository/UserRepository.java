package ua.dnipro.epam.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.dnipro.epam.homework.entity.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
