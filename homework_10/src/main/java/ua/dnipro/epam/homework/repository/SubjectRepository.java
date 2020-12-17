package ua.dnipro.epam.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.dnipro.epam.homework.entity.Subject;

import java.util.UUID;

public interface SubjectRepository extends JpaRepository<Subject, UUID> {
}
