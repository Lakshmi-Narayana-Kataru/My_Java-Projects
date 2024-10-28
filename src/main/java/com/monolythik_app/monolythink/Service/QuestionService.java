package com.monolythik_app.monolythink.Service;


import com.monolythik_app.monolythink.Modal.Questions;
import com.monolythik_app.monolythink.Repositary.QuestionRepositary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private QuestionRepositary questionRepositary;
    public QuestionService(QuestionRepositary questionRepositary) {
        this.questionRepositary = questionRepositary;
    }

    public Questions addQuestion(Questions question ) {

        return questionRepositary.save(question);
    }

    public Questions getQuestion(int id) {
        return questionRepositary.findById(id).get();
    }

    public List<Questions> getAllQuestions() {

        return questionRepositary.findAll();
    }

    public List<Questions> getQuestionsByCategory(String category) {

        return questionRepositary.getQuestionsByCategory(category);
    }
}
