package com.github.angelikac.trivia_quiz_game.entity.game;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Entity
@Table(name = "questions")
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    private Difficulty difficulty;

    @Column(name = "question_text")
    private String question;

    @JoinColumn(name = "category_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private CategoryEntity category;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

    public List<Answer> getAnswers() {
        List<Answer> shuffleList = new ArrayList<>(answers);
        Collections.shuffle(shuffleList);
        return shuffleList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
