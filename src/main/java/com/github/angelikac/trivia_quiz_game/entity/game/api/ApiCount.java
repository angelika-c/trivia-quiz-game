package com.github.angelikac.trivia_quiz_game.entity.game.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiCount {

    @JsonProperty("category_question_count")
    private CategoryQuestionCount categoryQuestionCount;

    public CategoryQuestionCount getCategoryQuestionCount() {
        return categoryQuestionCount;
    }

    public static class CategoryQuestionCount {

        @JsonProperty("total_question_count")
        private int totalQuestionCount;

        public int getTotalQuestionCount() {
            return totalQuestionCount;
        }
    }
}