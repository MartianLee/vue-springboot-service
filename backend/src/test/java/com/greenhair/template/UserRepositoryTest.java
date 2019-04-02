package com.greenhair.template;

import java.util.List;
import java.util.Optional;

import com.greenhair.template.domain.users.Users;
import com.greenhair.template.domain.users.UsersRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.java.Log;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class UserRepositoryTest {
    @Autowired
	UsersRepository usersRepository;
	
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
	public void testUsers() {
		Optional<Users> result = usersRepository.findById(85L);
		result.ifPresent(member -> log.info("member " + member));
	}

}