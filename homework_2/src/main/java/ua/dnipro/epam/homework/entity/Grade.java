package ua.dnipro.epam.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Grade {

    private Long id;

    private String result;

    private Long testId;

    private Long userId;
}
