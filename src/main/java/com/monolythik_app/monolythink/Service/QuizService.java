package com.monolythik_app.monolythink.Service;


import com.monolythik_app.monolythink.Modal.QuestionWrapper;
import com.monolythik_app.monolythink.Modal.Questions;
import com.monolythik_app.monolythink.Modal.Quiz;
import com.monolythik_app.monolythink.Modal.Response;
import com.monolythik_app.monolythink.Repositary.QuestionRepositary;
import com.monolythik_app.monolythink.Repositary.QuizRepositary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class QuizService {

    private QuizRepositary quizRepositary;
    private QuestionRepositary questionRepositary;
    public QuizService(QuizRepositary quizRepositary,QuestionRepositary questionRepositary) {
        this.quizRepositary = quizRepositary;
        this.questionRepositary = questionRepositary;
    }

    public ResponseEntity<String> createQuiz(String category, String title, int numQ) {
        List<Questions> questions = questionRepositary.findRandomQuestionsByCategory(category,numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepositary.save(quiz);
        return new ResponseEntity<>("created", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizById(int id) {

        Optional<Quiz> quiz  = quizRepositary.findById(id);

        List<Questions> questions = quiz.get().getQuestions();

        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        for(Questions q :questions){

            QuestionWrapper  qw = new QuestionWrapper(q.getId(),q.getQuestion(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionWrappers.add(qw);
        }
        return new ResponseEntity<>(questionWrappers,HttpStatus.OK);
    }


    public ResponseEntity<Integer> getQuizScore(int id, List<Response> responses) {

        Optional<Quiz> quiz = quizRepositary.findById(id);
        List<Questions> questions = quiz.get().getQuestions();
        int score = 0;
        int i=0;
        for(Response r :responses){
           if(r.getResponse().equals(questions.get(i).getRightAnswer()))
               score++;
           i++;
        }
        return new ResponseEntity<>(score,HttpStatus.OK);
    }
}
