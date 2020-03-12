package com.github.angelikac.trivia_quiz_game.entity.game.api;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.angelikac.trivia_quiz_game.entity.game.CategoryEntity;

import java.util.List;


public class CategoryApiResults {

    @JsonProperty("trivia_categories")
    private List<CategoryEntity> triviaCategories;

    public List<CategoryEntity> getTriviaCategories() {
        return triviaCategories;
    }
}
