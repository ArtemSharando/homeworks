package ua.dnipro.epam.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.dnipro.epam.homework.entity.Answer;
import ua.dnipro.epam.homework.entity.Question;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestWithQuestionsAndAnswers {

    private String name;

    private int time;

    private List<Question> questions;

    private List<Answer> answers;
}
