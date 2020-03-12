package com.github.angelikac.trivia_quiz_game.entity.game.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringEscapeUtils.unescapeHtml4;

public class QuestionApi {

    private String category;

    private String difficulty;

    private String question;

    @JsonProperty("correct_answer")
    private String correctAnswer;

    @JsonProperty("incorrect_answers")
    private List<String> incorrectAnswers;


    public String getCategory() {
        return decode(category);
    }

    public String getDifficulty() {
        return decode(difficulty);
    }

    public String getQuestion() {
        return decode(question);
    }

    public String getCorrectAnswer() {
        return decode(correctAnswer);
    }

    public List<String> getIncorrectAnswers() {
        return incorrectAnswers.stream().map(this::decode).collect(Collectors.toList());
    }

    private String decode(String value) {
        return unescapeHtml4(value);
    }
}