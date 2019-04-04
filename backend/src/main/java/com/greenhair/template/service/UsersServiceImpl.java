package com.greenhair.template.service;

import java.util.Objects;
import java.util.UUID;

import com.greenhair.template.domain.users.Users;
import com.greenhair.template.domain.users.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service("memberService")
public class UsersServiceImpl implements UsersService{
	
	private static final String PROFILE_DEFAULT_PATH = "/profile/0/profile_default.jpg";
	private static final String SIGNIN_EXCEPTION_MSG = "로그인정보가 일치하지 않습니다.";
	private static final String EMAIL_EXIST_EXCEPTION_MSG = "이미 계정이 존재합니다.";
	private static final String NICKNAME_EXIST_EXCEPTION_MSG = "이미 닉네임이 존재합니다.";
	
	@Autowired
	private UsersRepository usersRepository;
	
	public Users signup(Users users) {
		String email = users.getEmail();
		this.validate(email);
		this.setupForSave(users);
		Users createdUsers = usersRepository.save(users);
		return createdUsers;
	}

	private void setupForSave(Users users){
		String password = users.getPassword();
		String encodedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
		users.setPassword(encodedPassword);
	}

	@Override
	public boolean isExist(String email) {
		boolean isExist = false;
		Users member = usersRepository.findByEmail(email);
		if(member != null){
		    isExist = true;
		}
		return isExist;
	}
	
	@Override
	public void validate(String email){
		if( this.isExist(email) ){
			throw new IllegalStateException(EMAIL_EXIST_EXCEPTION_MSG);
		}
	}

	@Override
	public Users signin(String email, String password) {
		Users users = usersRepository.findByEmail(email);
		Objects.requireNonNull(users, SIGNIN_EXCEPTION_MSG);
		
		if( ! this.isAccordPassword(users, password)){
			throw new IllegalStateException(SIGNIN_EXCEPTION_MSG);
		}
		
		return users;
	}

	private boolean isAccordPassword(Users users, String password){
		String encodedPassword = users.getPassword();
		return BCrypt.checkpw(password, encodedPassword);
	}

	@Override
	public void updateInfo(String nickname, String password, long memberId) {
		Users currentMember = usersRepository.getOne(memberId);
		this.updateName(currentMember, nickname);
		this.updatePassword(currentMember, password);
		usersRepository.save(currentMember);
	}
	
	private void updateName(Users users, String nickname){
		String currentNickname = users.getName();
		Users searchedMember = usersRepository.findByName(nickname);
		if(currentNickname.equals(nickname) || searchedMember == null ){
			users.setName(nickname);
		}else{
			throw new IllegalStateException(NICKNAME_EXIST_EXCEPTION_MSG);
		}
	}
	
	private void updatePassword(Users users, String password){
		if(!password.equals("")){
			String encodePassword = BCrypt.hashpw(password, BCrypt.gensalt());
			users.setPassword(encodePassword);
		}
	}

	@Override
	public Users findByMemberId(long memberId) {
		return usersRepository.findById(memberId).get();
	}
	
	private String getRandomImageName(){
		return UUID.randomUUID().toString().replaceAll("-", "")+".jpg";
	}
}