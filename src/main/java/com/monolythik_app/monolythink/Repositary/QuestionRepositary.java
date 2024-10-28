package com.monolythik_app.monolythink.Repositary;

import com.monolythik_app.monolythink.Modal.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepositary  extends JpaRepository<Questions, Integer> {

    List<Questions> getQuestionsByCategory(String category);

    @Query(value ="select * from questions q where q.category=:category  order by rand() limit :numQ", nativeQuery = true)
    List<Questions> findRandomQuestionsByCategory(String category, int numQ);


}
