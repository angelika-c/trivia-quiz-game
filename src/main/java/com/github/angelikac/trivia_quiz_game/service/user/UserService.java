package com.github.angelikac.trivia_quiz_game.service.user;

import com.github.angelikac.trivia_quiz_game.dto.UserDto;
import com.github.angelikac.trivia_quiz_game.entity.user.Statistic;
import com.github.angelikac.trivia_quiz_game.entity.user.User;
import com.github.angelikac.trivia_quiz_game.repository.StatisticRepository;
import com.github.angelikac.trivia_quiz_game.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public static final ModelMapper MODEL_MAPPER = new ModelMapper();

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private StatisticRepository statisticRepository;

    @Autowired
    public UserService(final PasswordEncoder passwordEncoder, final UserRepository userRepository,
                       final StatisticRepository statisticRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.statisticRepository = statisticRepository;
    }

    public UserDto saveUser(UserDto userDto) {
        User entity = mapUser(userDto);
        saveUserPassword(entity);
        saveUserStatistic(entity);

        return mapUserDto(userRepository.save(entity));
    }

    private void saveUserPassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    private void saveUserStatistic(User user) {
        Statistic statistic = new Statistic();
        statistic.setUser(user);
        statisticRepository.save(statistic);
        user.setStatistic(statistic);
    }

    private User mapUser(UserDto userDto) {
        return MODEL_MAPPER.map(userDto, User.class);
    }

    private UserDto mapUserDto(User user) {
        return MODEL_MAPPER.map(user, UserDto.class);
    }

    User getByUsername(final String username) {
        return userRepository.findUserByUsernameIgnoringCase(username);
    }

}
