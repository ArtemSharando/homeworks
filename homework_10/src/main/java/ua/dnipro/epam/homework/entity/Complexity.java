package ua.dnipro.epam.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.SEQUENCE;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Complexity {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "complexity_seq")
    private Long id;

    private String name;
}