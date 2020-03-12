package com.github.angelikac.trivia_quiz_game.service.game.api_service;

import com.github.angelikac.trivia_quiz_game.entity.game.Answer;
import com.github.angelikac.trivia_quiz_game.entity.game.CategoryEntity;
import com.github.angelikac.trivia_quiz_game.entity.game.Difficulty;
import com.github.angelikac.trivia_quiz_game.entity.game.QuestionEntity;
import com.github.angelikac.trivia_quiz_game.entity.game.api.QuestionApi;
import com.github.angelikac.trivia_quiz_game.repository.AnswerRepository;
import com.github.angelikac.trivia_quiz_game.repository.CategoryRepository;
import com.github.angelikac.trivia_quiz_game.repository.QuestionRepository;
import com.github.angelikac.trivia_quiz_game.service.game.api_service.OpenDBApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionApiService {

    private OpenDBApiService questionApiService;
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public QuestionApiService(OpenDBApiService questionApiService, QuestionRepository questionRepository,
                              AnswerRepository answerRepository, CategoryRepository categoryRepository) {
        this.questionApiService = questionApiService;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public void save(){
        final List<CategoryEntity> categoriesListFromApi = questionApiService.getCategoriesListFromApi();
        for (final CategoryEntity category : categoriesListFromApi) {
            final List<QuestionApi> questionByCategory = questionApiService
                    .getQuestionByCategory(category.getId());
            for (final QuestionApi questionApi : questionByCategory) {
                QuestionEntity entity = map(questionApi);
                questionRepository.save(entity);
                answerRepository.saveAll(entity.getAnswers());
            }
        }
    }

    private QuestionEntity map(QuestionApi api) {

        QuestionEntity entity = new QuestionEntity();
        entity.setQuestion(api.getQuestion());
        entity.setCategory(categoryRepository.findByName(api.getCategory()));

        final String difficulty = api.getDifficulty();
        entity.setDifficulty(Difficulty.valueOf(difficulty.toUpperCase()));

        final List<String> incorrectAnswers = api.getIncorrectAnswers();
        final List<Answer> answerList = new ArrayList<>();

        for (final String answer : incorrectAnswers) {
            Answer myAnswer = new Answer();
            myAnswer.setCorrect(false);
            myAnswer.setAnswer(answer);
            myAnswer.setQuestion(entity);
            answerList.add(myAnswer);
        }

        Answer correct = new Answer();
        correct.setAnswer(api.getCorrectAnswer());
        correct.setCorrect(true);
        correct.setQuestion(entity);

        answerList.add(correct);
        entity.setAnswers(answerList);
        return entity;
    }

}