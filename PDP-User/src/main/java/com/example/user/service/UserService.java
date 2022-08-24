package com.example.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.user.entity.UsersEntity;
import com.example.user.repository.UsersRepository;

@Service
public class UserService {
	
	//Map<Integer, User> userMap = new HashMap<>();
	@Autowired
	UsersRepository userRepository ;
	
	public List<UsersEntity> getList(){
		return userRepository.findAll();
	}

	public UsersEntity getUser(int id){
		return userRepository.findById(id).get();
	}

	public UsersEntity addUser(UsersEntity user){
		userRepository.save(user);
		return user;
	}

	public UsersEntity update(UsersEntity user) {
		userRepository.save(user);
		return null;
	}

	public void deleteById(int userId) {
		userRepository.delete(getUser(userId));
	}

	public UserService() {
		// TODO Auto-generated constructor stub
	}
}
