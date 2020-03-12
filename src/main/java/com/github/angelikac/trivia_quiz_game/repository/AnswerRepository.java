package com.github.angelikac.trivia_quiz_game.repository;

import com.github.angelikac.trivia_quiz_game.entity.game.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
