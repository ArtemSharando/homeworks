package ua.dnipro.epam.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Answer {

    private Long id;

    private String variant;

    private int counterAnswer;

    private boolean correctAnswer;

    private Long questionId;

    private Long testId;
}
