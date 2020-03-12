package com.github.angelikac.trivia_quiz_game.controller;

import com.github.angelikac.trivia_quiz_game.entity.game.CategoryEntity;
import com.github.angelikac.trivia_quiz_game.entity.game.Difficulty;
import com.github.angelikac.trivia_quiz_game.entity.game.QuestionEntity;
import com.github.angelikac.trivia_quiz_game.entity.quiz.Quiz;
import com.github.angelikac.trivia_quiz_game.service.game.CategoryService;
import com.github.angelikac.trivia_quiz_game.service.game.QuestionService;
import com.github.angelikac.trivia_quiz_game.service.quiz.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/game")
public class GameController {

    private QuestionService questionService;
    private CategoryService categoryService;
    private QuizService quizService;

    @Autowired
    public GameController(QuestionService questionService, CategoryService categoryService, QuizService quizService) {
        this.questionService = questionService;
        this.categoryService = categoryService;
        this.quizService = quizService;
    }

    @GetMapping
    public String game() {
        return "game";
    }

    @GetMapping("/categorieslist")
    public String categoriesList(Model model) {

        final List<CategoryEntity> categories = categoryService.findAllCategoriesFromDB();
        model.addAttribute("categoryList", categories);
        return "categories";
    }

    @GetMapping("/levels")
    public String levelOfDifficultyList(Model model) {
        final List<Difficulty> levelOfDifficulty = questionService.findAllLevelOfDifficulty();
        model.addAttribute("difficultyList", levelOfDifficulty);
        return "levelsofdifficulty";
    }

    @GetMapping("/category")
    public String questionByCategory(@RequestParam(name = "id") Long categoryId,
                                     Principal principal) {
        if (principal != null) {
            Quiz createdQuiz = quizService.createNewQuizByCategory(principal.getName(), categoryId);
            return "redirect:/quiz/" + createdQuiz.getId();
        }
        return "redirect:/login";
    }

    @GetMapping("/difficulty")
    public String questionByDifficulty(@RequestParam(name = "difficulty") String difficulty,
                                       Principal principal) {
        if (principal != null) {
            Quiz createdQuiz = quizService.createNewQuizByDifficulty(principal.getName(), difficulty);
            return "redirect:/quiz/" + createdQuiz.getId();
        }
        return "redirect:/login";
    }

    @ModelAttribute("question")
    public QuestionEntity question() {
        return new QuestionEntity();
    }
}