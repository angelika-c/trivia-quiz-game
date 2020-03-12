package com.github.angelikac.trivia_quiz_game.service.quiz;

import com.github.angelikac.trivia_quiz_game.entity.game.QuestionEntity;
import com.github.angelikac.trivia_quiz_game.entity.quiz.Quiz;
import com.github.angelikac.trivia_quiz_game.entity.quiz.UserAnswer;
import com.github.angelikac.trivia_quiz_game.exception.EndOfQuizException;
import com.github.angelikac.trivia_quiz_game.repository.AnswerRepository;
import com.github.angelikac.trivia_quiz_game.repository.QuizRepository;
import com.github.angelikac.trivia_quiz_game.repository.UserAnswerRepository;
import com.github.angelikac.trivia_quiz_game.service.game.QuestionService;
import com.github.angelikac.trivia_quiz_game.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuestionService questionService;
    private final UserService userService;
    private final AnswerRepository answerRepository;
    private final UserAnswerRepository userAnswerRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository, QuestionService questionService, UserService userService,
                       AnswerRepository answerRepository, UserAnswerRepository userAnswerRepository) {
        this.quizRepository = quizRepository;
        this.questionService = questionService;
        this.userService = userService;
        this.answerRepository = answerRepository;
        this.userAnswerRepository = userAnswerRepository;
    }

    public Quiz createNewQuizByCategory(String username, Long categoryId) {
        Quiz quiz = new Quiz();
        quiz.setId(UUID.randomUUID().toString());
        quiz.setOwner(userService.getByUsername(username));
        while (quiz.getUserAnswerList().size() < 5) {
            QuestionEntity question = questionService.findQuestionByCategory(categoryId);
            UserAnswer userAnswer = new UserAnswer(question, null);

            userAnswerRepository.save(userAnswer);
            quiz.getUserAnswerList().add(userAnswer);
        }
        return saveQuiz(quiz);
    }

    public Quiz createNewQuizByDifficulty(final String username, final String difficulty) {
        Quiz quiz = new Quiz();
        quiz.setId(UUID.randomUUID().toString());
        quiz.setOwner(userService.getByUsername(username));
        while (quiz.getUserAnswerList().size() < 5) {
            QuestionEntity question = questionService.findQuestionByDifficulty(difficulty);
            UserAnswer userAnswer = new UserAnswer(question, null);

            userAnswerRepository.save(userAnswer);
            quiz.getUserAnswerList().add(userAnswer);
        }
        return saveQuiz(quiz);
    }

    private Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public QuestionEntity getQuizQuestion(final String id, final Integer questionNumber) {

        Optional<Quiz> quizFromRepo = quizRepository.findById(id);
        if (quizFromRepo.isPresent()) {
            Quiz quiz = quizFromRepo.get();

            if (questionNumber != null && questionNumber >= quiz.getUserAnswerList().size()) {
                throw new EndOfQuizException("End of game");
            }

            if (questionNumber == null) {
                return quiz.getUserAnswerList().get(0).getQuestionEntity();
            } else {
                return quiz.getUserAnswerList().get(questionNumber).getQuestionEntity();
            }
        }
        throw new EntityNotFoundException("Cannot find quiz");
    }

    public void saveAnswer(final String id, final Integer questionNumber, final Long answerId) {
        Optional<Quiz> quizRepo = quizRepository.findById(id);
        if (quizRepo.isPresent()) {
            Quiz quiz = quizRepo.get();

            UserAnswer userAnswer = quiz.getUserAnswerList().get(questionNumber);
            userAnswer.setUserAnswer(answerRepository.findById(answerId).get());

            userAnswerRepository.save(userAnswer);

            quiz.getUserAnswerList().set(questionNumber, userAnswer);
            quizRepository.save(quiz);
        }
    }
}