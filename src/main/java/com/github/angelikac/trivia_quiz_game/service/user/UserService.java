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
        User user = saveUserStatistic(userDto);
        return MODEL_MAPPER.map(userRepository.save(user), UserDto.class);
    }

    private User saveUserPassword(UserDto userDto){
        User user = MODEL_MAPPER.map(userDto, User.class);
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(encodedPassword);
        return user;
    }

    private User saveUserStatistic(UserDto userDto){
        User user = saveUserPassword(userDto);
        Statistic statistic = new Statistic();
        statistic.setUser(user);
        statisticRepository.save(statistic);
        user.setStatistic(statistic);
        return user;
    }

    User getByUsername(final String username) {
        return userRepository.findUserByUsernameIgnoringCase(username);
    }

}
