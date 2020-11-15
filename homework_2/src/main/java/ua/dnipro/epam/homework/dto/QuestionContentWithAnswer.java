package ua.dnipro.epam.homework.dto;

import ua.dnipro.epam.homework.entity.Answer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionContentWithAnswer {

    private String content;

    private int counterQuestion;

    private List<Answer> answerOfQ;

}
