package ua.dnipro.epam.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.dnipro.epam.homework.entity.Grade;

import java.util.UUID;

public interface GradeRepository extends JpaRepository<Grade, UUID> {
}
