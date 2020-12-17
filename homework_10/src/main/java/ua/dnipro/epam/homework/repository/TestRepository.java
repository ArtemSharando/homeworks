package ua.dnipro.epam.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.dnipro.epam.homework.entity.Test;

import java.util.UUID;

public interface TestRepository extends JpaRepository<Test, UUID> {
}
