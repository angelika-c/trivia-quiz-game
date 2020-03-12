package com.github.angelikac.trivia_quiz_game.controller;

import com.github.angelikac.trivia_quiz_game.service.game.api_service.CategoryApiService;
import com.github.angelikac.trivia_quiz_game.service.game.api_service.QuestionApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

@Controller
public class DatabaseController {

    private QuestionApiService questionApiService;
    private CategoryApiService categoryApiService;

    @Autowired
    public DatabaseController(QuestionApiService questionApiService, CategoryApiService categoryApiService) {
        this.questionApiService = questionApiService;
        this.categoryApiService = categoryApiService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void saveQuestionInDb() {
        categoryApiService.saveAllCategoriesInDb();
        questionApiService.save();
    }
}