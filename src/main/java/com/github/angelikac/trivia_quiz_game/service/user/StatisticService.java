package com.github.angelikac.trivia_quiz_game.service.user;

import com.github.angelikac.trivia_quiz_game.entity.quiz.Quiz;
import com.github.angelikac.trivia_quiz_game.entity.user.Statistic;
import com.github.angelikac.trivia_quiz_game.entity.user.User;
import com.github.angelikac.trivia_quiz_game.exception.EntityNotFoundException;
import com.github.angelikac.trivia_quiz_game.repository.QuizRepository;
import com.github.angelikac.trivia_quiz_game.repository.StatisticRepository;
import com.github.angelikac.trivia_quiz_game.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class StatisticService {

    private final StatisticRepository statisticRepository;
    private final UserRepository userRepository;
    private final QuizRepository quizRepository;

    @Autowired
    public StatisticService(StatisticRepository statisticRepository, UserRepository userRepository, QuizRepository quizRepository) {
        this.statisticRepository = statisticRepository;
        this.userRepository = userRepository;
        this.quizRepository = quizRepository;
    }

    @Transactional
    public Statistic updateStatistic(final String quizId, String username) {
        final User user = userRepository.findUserByUsernameIgnoringCase(username);
        final Optional<Quiz> quizById = quizRepository.findById(quizId);

        if (quizById.isPresent()) {
            Quiz quiz = quizById.get();
            final Statistic entity = findEntity(user);

            if(!quiz.isFinished()){
                int countCorrectAnswers = quiz.getUserAnswerList()
                        .stream()
                        .mapToInt(userAnswer -> userAnswer.getUserAnswer().getCorrect() ? 1 : 0).sum();
                int countAllAnswers = quiz.getUserAnswerList().size();
                entity.setNumberOfAnsweredQuestions(entity.getNumberOfAnsweredQuestions() + countAllAnswers);
                entity.setNumberOfPoints(entity.getNumberOfPoints() + countCorrectAnswers);
                quiz.setFinished(true);

                quizRepository.save(quiz);
                statisticRepository.save(entity);
            }

            return entity;
        }
        throw new EntityNotFoundException(Statistic.class);
    }

    private Statistic findEntity(User user) {
        return statisticRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException(Statistic.class));
    }
}