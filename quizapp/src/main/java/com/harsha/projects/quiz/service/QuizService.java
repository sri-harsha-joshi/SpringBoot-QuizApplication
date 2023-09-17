package com.harsha.projects.quiz.service;

import com.harsha.projects.quiz.dao.QuestionDao;
import com.harsha.projects.quiz.dao.QuizDao;
import com.harsha.projects.quiz.model.Question;
import com.harsha.projects.quiz.model.QuestionWrapper;
import com.harsha.projects.quiz.model.Quiz;
import com.harsha.projects.quiz.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public String createQuiz(String category , int numQ,String title){
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category,numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return "quiz created";
    }

    public List<QuestionWrapper> getQuizQuestions(Integer id) {
      Optional<Quiz> quiz = quizDao.findById(id);
      List<QuestionWrapper> questionsForUsers = new ArrayList<>();
      List<Question> questionsFromDb =quiz.get().getQuestions();
      for(Question q : questionsFromDb){
          QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
          questionsForUsers.add(qw);
      }
      return questionsForUsers;
    }

    public Integer calculateResult(Integer id, List<Response> responses) {
        Quiz quiz =quizDao.findById(id).get();
        List<Question> questions =quiz.getQuestions();
        int i=0;
        int right = 0;
        for(Response response :responses){
            if (response.getResponse().equals(questions.get(i).getRightAnswer())) {

                right++;
            }
                i++;
        }
        return right;
    }
}
