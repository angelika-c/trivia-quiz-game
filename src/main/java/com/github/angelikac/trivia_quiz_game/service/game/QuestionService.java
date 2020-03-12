package com.github.angelikac.trivia_quiz_game.service.game;

import com.github.angelikac.trivia_quiz_game.entity.game.Difficulty;
import com.github.angelikac.trivia_quiz_game.entity.game.QuestionEntity;
import com.github.angelikac.trivia_quiz_game.repository.CategoryRepository;
import com.github.angelikac.trivia_quiz_game.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class QuestionService {

    private static final Random RANDOM = new Random();

    private final QuestionRepository questionRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, CategoryRepository categoryRepository) {
        this.questionRepository = questionRepository;
        this.categoryRepository = categoryRepository;
    }

    public QuestionEntity findQuestionByCategory(final Long id) {
        final List<QuestionEntity> allByCategory = questionRepository
                .findAllByCategory(categoryRepository.getOne(id));
        final int idQuestion = RANDOM.nextInt(allByCategory.size());

        return allByCategory.get(idQuestion);
    }

    public QuestionEntity findQuestionByDifficulty(String difficulty){
        final List<QuestionEntity> allByDifficulty = questionRepository
                .findAllByDifficulty(Difficulty.valueOf(difficulty.toUpperCase()));
        final int questionId = RANDOM.nextInt(allByDifficulty.size());
        return allByDifficulty.get(questionId);
    }

    public List<Difficulty> findAllLevelOfDifficulty(){
        return Arrays.asList(Difficulty.values());
    }
}