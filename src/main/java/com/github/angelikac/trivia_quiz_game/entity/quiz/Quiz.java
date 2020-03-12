package com.github.angelikac.trivia_quiz_game.entity.quiz;

import com.github.angelikac.trivia_quiz_game.entity.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @OneToMany
    private List<UserAnswer> userAnswerList = new ArrayList<>();

    @ManyToOne
    private User owner;

    @Column(nullable = false, columnDefinition="boolean default false")
    private boolean finished = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<UserAnswer> getUserAnswerList() {
        return userAnswerList;
    }

    public void setUserAnswerList(List<UserAnswer> userAnswerList) {
        this.userAnswerList = userAnswerList;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}