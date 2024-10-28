package com.monolythik_app.monolythink.Controller;


import com.monolythik_app.monolythink.Modal.QuestionWrapper;
import com.monolythik_app.monolythink.Modal.Response;
import com.monolythik_app.monolythink.Service.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private QuizService quizService;
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }


    @PostMapping("/create")
    public ResponseEntity<String>  createQuiz(@RequestParam String category, @RequestParam String title, @RequestParam int numQ) {

        quizService.createQuiz(category, title, numQ);
        return ResponseEntity.ok("Quiz created");
    }


    @GetMapping("/all/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizById(@PathVariable int id) {
        return quizService.getQuizById(id);
    }

    @PostMapping("/score/{id}")
    public ResponseEntity<Integer> getQuizScore(@PathVariable int id, @RequestBody List<Response> responses) {

        return quizService.getQuizScore(id,responses);


    }

}
