package com.greenhair.template;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.greenhair.template.domain.users.Users;
import com.greenhair.template.domain.users.UsersRepository;
import com.greenhair.template.service.JwtService;
import com.greenhair.template.web.UsersController;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserAuthTest {
    @Autowired
    UsersRepository usersRepository;
    
    @Autowired
    UsersController userController;
    
    @Autowired
    JwtService jwtService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @After
    public void cleanup() {
        usersRepository.deleteAll();
    }
    
    @Test
	public void createUser() {
        //given
        String response = userController.create(Users.builder()
                .email("testuser@gmail.com")
                .password("testuser")
                .name("테스트 유저")
                .build());
        
        //when
        Users users = usersRepository.findByEmail("testuser@gmail.com");

        //then
        assertThat(users.getEmail(), is("testuser@gmail.com"));
        assertThat(users.getName(), is("테스트 유저"));
        assertThat(response, is("success"));
    }

    @Test
	public void loginUser() {
        //given
        this.createUser();

        //when
        String token = userController.login(Users.builder()
        .email("testuser@gmail.com")
        .password("testuser")
        .name("테스트 유저")
        .build());

        //then
        assertTrue(jwtService.isUsable(token));
    }

    @Test
	public void getUser() {
    }
}