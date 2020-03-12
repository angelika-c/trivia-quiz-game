package com.github.angelikac.trivia_quiz_game.repository;

import com.github.angelikac.trivia_quiz_game.entity.user.Statistic;
import com.github.angelikac.trivia_quiz_game.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Long> {

    Optional<Statistic> findByUser(User user);
}
