package com.harsha.projects.quiz.controller;

import com.harsha.projects.quiz.model.QuestionWrapper;
import com.harsha.projects.quiz.model.Quiz;
import com.harsha.projects.quiz.model.Response;
import com.harsha.projects.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping ("create")
    public String createQuiz(@RequestParam String category,
                @RequestParam int numQ ,@RequestParam String title){
        return quizService.createQuiz(category,numQ,title);
    }
    @GetMapping ("get/{id}")
    public List<QuestionWrapper> getQuizQuestions(@PathVariable Integer id) {
        return quizService.getQuizQuestions(id);
    }
    @PostMapping ("submit/{id}")
    public Integer submitQuiz(@PathVariable Integer id ,@RequestBody List<Response> responses ){
        return quizService.calculateResult(id,responses);
    }
}
