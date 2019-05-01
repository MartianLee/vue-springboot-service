package com.greenhair.template;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.greenhair.template.domain.users.Users;
import com.greenhair.template.domain.users.UsersRepository;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
	UsersRepository usersRepository;

    @After
    public void cleanup() {
        usersRepository.deleteAll();
    }

	@Test
	public void insertTest() {
        //given
		for(int i=0; i<20; i++) {
			Users users = new Users("hihi" + i + "@hihi.com", "pw" + i, "user" + i);
			// UserRole role = new UserRole();
			// if(i <= 14) {
			// 	role.setRoleName("BASIC");
			// }else if(i <= 18) {
			// 	role.setRoleName("MANAGER");
			// }else {
			// 	role.setRoleName("ADMIN");
			// }
			// users.setRoles(Arrays.asList(role));
			usersRepository.save(users);
        }
        //when
        List<Users> usersList = usersRepository.findAll();

        //then
        Users users = usersList.get(1);
        assertThat(users.getName(), is("user1"));
        assertThat(users.getEmail(), is("hihi1@hihi.com"));
    }
    
    @Test
    public void BaseTimeEntity_등록 () {
        //given
        LocalDateTime now = LocalDateTime.now();
        usersRepository.save(Users.builder()
                .email("jojoldu@gmail.com")
                .password("test")
                .name("테스트 유저")
                .build());
        //when
        List<Users> usersList = usersRepository.findAll();

        //then
        Users users = usersList.get(0);
        assertThat(users.getName(), is("테스트 유저"));
        assertTrue(users.getCreatedDate().isAfter(now));
        assertTrue(users.getModifiedDate().isAfter(now));
    }
	
	@Test
	public void testUsers() {
        Optional<Users> result = usersRepository.findById(85L);
        Users user = result.get();
        System.out.println(user.getName());
		// result.ifPresent(member -> log.info("member " + member));
    }
    
    @Test
    public void testLogin() {
        Users user = new Users("martionlee@gmail.com", "1234", "admin");
        usersRepository.save(user);
        
        
    }

}