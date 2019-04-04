package com.greenhair.template.service;

import com.greenhair.template.domain.users.Users;

import org.springframework.stereotype.Service;

@Service
public interface UsersService {

    Users signup(Users usersMaster);

    boolean isExist(String email);
    
    void validate(String email);

    Users signin(String email, String password);
    
	void updateInfo(String nickname, String password, long memberId);

	Users findByMemberId(long memberId);

}