package ua.dnipro.epam.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestSubjectComplexity {

    private Long id;

    private String name;

    private Integer numberOfQuestion;

    private String subject;

    private String complexity;

}
