package ua.dnipro.epam.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "question_seq")
    private Long id;

    private String content;

    private int counterQuestion;

    private int numberOfAnswer;

    @ManyToOne
    private Test test;

}
