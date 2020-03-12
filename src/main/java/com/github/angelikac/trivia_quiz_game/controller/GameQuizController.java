package com.github.angelikac.trivia_quiz_game.controller;

import com.github.angelikac.trivia_quiz_game.entity.game.QuestionEntity;
import com.github.angelikac.trivia_quiz_game.entity.user.Statistic;
import com.github.angelikac.trivia_quiz_game.exception.EndOfQuizException;
import com.github.angelikac.trivia_quiz_game.service.quiz.QuizService;
import com.github.angelikac.trivia_quiz_game.service.user.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/quiz/")
public class GameQuizController {

    private QuizService quizService;
    private StatisticService statisticService;

    @Autowired
    public GameQuizController(QuizService quizService, StatisticService statisticService) {
        this.quizService = quizService;
        this.statisticService = statisticService;
    }

    @GetMapping("/{id}")
    public String getQuizByCategory(@PathVariable(name = "id") String id,
                                    @RequestParam(name = "questionNumber", required = false) Integer questionNumber,
                                    Model model) {

        try {
            QuestionEntity questionEntity = quizService.getQuizQuestion(id, questionNumber);
            model.addAttribute("questionEntity", questionEntity);
            model.addAttribute("questionNumber", questionNumber == null ? 0 : questionNumber);
            model.addAttribute("quizid", id);
            return "question";
        } catch (EndOfQuizException endOfQuiz) {
            return "redirect:/quiz/endgame/" + id;
        }
    }

    @GetMapping("/{id}/{questionNumber}/answer/{questionEntityId}")
    public String getQuizByCategory(@PathVariable(name = "id") String id,
                                    @PathVariable(name = "questionNumber") Integer questionNumber,
                                    @PathVariable(name = "questionEntityId") Long questionEntityId,
                                    @RequestParam(name = "answer") Long answerId) {
        quizService.saveAnswer(id, questionNumber, answerId);
        return "redirect:/quiz/" + id + "?questionNumber=" + (questionNumber + 1);
    }

    @GetMapping("/endgame/{id}")
    public String endQuiz(@PathVariable(name = "id") String quizId, Model model, Principal principal) {

        Statistic statistic = statisticService.updateStatistic(quizId, principal.getName());
        model.addAttribute("statistic", statistic);

        return "endgame";
    }
}