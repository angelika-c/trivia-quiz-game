package com.github.angelikac.trivia_quiz_game.repository;

import com.github.angelikac.trivia_quiz_game.entity.quiz.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, String> {
}