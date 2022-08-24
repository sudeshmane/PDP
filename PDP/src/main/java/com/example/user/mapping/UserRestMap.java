package com.example.user.mapping;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.user.entity.UsersEntity;
import com.example.user.model.Book;
import com.example.user.model.User;
import com.example.user.service.UserService;

@RestController
public class UserRestMap {

	@Autowired
	UserService userService;
	
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	DiscoveryClient discovery;
	
	@GetMapping("/users")
	public List<Book> getBookList(){
		URI uri = discovery.getInstances("book-service").get(0).getUri();
		System.out.println(uri);
		//return (List<Book>) restTemplate.getForObject(uri+"/books/", List.class);
		List<Book> book = (List<Book>) restTemplate.getForObject("http://book-service/books", List.class);
		System.out.println("Afterrrrrr");
		return book;
		//return userService.getList();
	}
	
	@GetMapping("/users/{user_id}")
	public UsersEntity getBook(@PathVariable ("user_id") int userId){
		UsersEntity user = userService.getUser(userId);
		return user;
	}
	
	@PostMapping(value="/users")
	public UsersEntity addUser(@RequestBody UsersEntity user){
		return userService.addUser(user);
	}
	
	@PutMapping(value="/users/{user_id}")
	public UsersEntity updateUser(@PathVariable ("user_id") int userId, @RequestBody UsersEntity user){
		user.setId(userId);
		return userService.update(user);
	}
	
	@DeleteMapping(value="/users/{user_id}")
	public void deleteUser(@PathVariable ("user_id") int userId){
		 userService.deleteById(userId);
	}
}
