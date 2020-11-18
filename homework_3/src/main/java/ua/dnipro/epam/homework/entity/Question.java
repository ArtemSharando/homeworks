package ua.dnipro.epam.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    private Long id;

    private String content;

    private int counterQuestion;

    private int numberOfAnswer;

    private Long testId;

}
