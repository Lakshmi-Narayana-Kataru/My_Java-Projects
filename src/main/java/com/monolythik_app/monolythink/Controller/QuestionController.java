package com.monolythik_app.monolythink.Controller;

import com.monolythik_app.monolythink.Modal.Questions;
import com.monolythik_app.monolythink.Service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private QuestionService questionService;
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Questions>> getAllQuestions()
    {
        return new ResponseEntity<>(questionService.getAllQuestions() , HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<Questions> addQuestion(@RequestBody  Questions question)
    {
        return new ResponseEntity<>(questionService.addQuestion(question), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Questions> getQuestion(@PathVariable int id) {

        Questions question = questionService.getQuestion(id);
        if(question != null)
            return new ResponseEntity<>(question,HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/category/{category}")
    public ResponseEntity<List<Questions>> getQuestionsByCategory(@PathVariable String category) {

        List<Questions> questions = questionService.getQuestionsByCategory(category);
        for(Questions question : questions) {
            if (question != null)
                return new ResponseEntity<>(questions, HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND) ;
    }
}
