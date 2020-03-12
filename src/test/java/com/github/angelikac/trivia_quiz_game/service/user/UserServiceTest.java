package com.github.angelikac.trivia_quiz_game.service.user;

import com.github.angelikac.trivia_quiz_game.dto.UserDto;
import com.github.angelikac.trivia_quiz_game.entity.user.Statistic;
import com.github.angelikac.trivia_quiz_game.entity.user.User;
import com.github.angelikac.trivia_quiz_game.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    //TODO test for UserService
//    @Test
//    public void shouldSaveUserPasswordAndStatistic() {
//
//        UserService userService = mock(UserService.class);
//        ModelMapper modelMapper = mock(ModelMapper.class);
//
//        UserRepository userRepository = mock(UserRepository.class);
//        userRepository.save(modelMapper.map(prepareUserDtoData(), User.class));
//
//        User newuser = userService.getByUsername("newuser");
//
//        Assert.assertEquals(newuser.getUsername(), userService.saveUser(prepareUserDtoData()).getUsername());
//
//    }
//    private UserDto prepareUserDtoData(){
//        UserDto userDto = new UserDto();
//        userDto.setPassword("password");
//        userDto.setUsername("newUser");
//        return userDto;
//    }

}