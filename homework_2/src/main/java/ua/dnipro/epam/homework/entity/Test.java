package ua.dnipro.epam.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Test {

    private Long id;

    private String name;

    private int time;

    private int numberOfQuestions;

    private Long subjectId;

    private Long complexityId;

}
