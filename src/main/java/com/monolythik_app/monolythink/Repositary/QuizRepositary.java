package com.monolythik_app.monolythink.Repositary;


import com.monolythik_app.monolythink.Modal.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepositary extends JpaRepository<Quiz, Integer> {

}
