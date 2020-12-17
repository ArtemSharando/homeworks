package ua.dnipro.epam.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.dnipro.epam.homework.entity.Question;

import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, UUID> {
}
