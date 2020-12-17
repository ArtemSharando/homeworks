package ua.dnipro.epam.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.dnipro.epam.homework.entity.Complexity;

import java.util.UUID;

public interface ComplexityRepository extends JpaRepository<Complexity, UUID> {
}
