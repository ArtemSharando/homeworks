package ua.dnipro.epam.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.dnipro.epam.homework.entity.Answer;

import java.util.UUID;

public interface AnswerRepository extends JpaRepository<Answer, UUID> {
}
