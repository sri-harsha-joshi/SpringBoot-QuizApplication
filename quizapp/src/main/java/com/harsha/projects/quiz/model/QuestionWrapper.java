package com.harsha.projects.quiz.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionWrapper {
    private Integer id;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String questionTitle;
}
