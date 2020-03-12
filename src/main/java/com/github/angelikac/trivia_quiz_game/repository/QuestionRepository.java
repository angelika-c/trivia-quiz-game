package com.github.angelikac.trivia_quiz_game.repository;

import com.github.angelikac.trivia_quiz_game.entity.game.CategoryEntity;
import com.github.angelikac.trivia_quiz_game.entity.game.Difficulty;
import com.github.angelikac.trivia_quiz_game.entity.game.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    List<QuestionEntity> findAllByCategory(CategoryEntity category);
    List<QuestionEntity> findAllByDifficulty(Difficulty difficulty);

}
