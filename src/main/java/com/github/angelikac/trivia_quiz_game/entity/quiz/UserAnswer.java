package com.github.angelikac.trivia_quiz_game.entity.quiz;

import com.github.angelikac.trivia_quiz_game.entity.game.Answer;
import com.github.angelikac.trivia_quiz_game.entity.game.QuestionEntity;

import javax.persistence.*;

@Entity
public class UserAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private QuestionEntity questionEntity;

    @ManyToOne
    private Answer userAnswer;

    public UserAnswer() {
    }

    public UserAnswer(QuestionEntity questionEntity, Answer answer) {
        this.questionEntity = questionEntity;
        this.userAnswer = answer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionEntity getQuestionEntity() {
        return questionEntity;
    }

    public void setQuestionEntity(QuestionEntity questionEntity) {
        this.questionEntity = questionEntity;
    }

    public Answer getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(Answer userAnswer) {
        this.userAnswer = userAnswer;
    }
}