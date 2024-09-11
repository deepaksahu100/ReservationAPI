package org.jsp.bookmyticket.service;

import java.util.Optional;

import org.jsp.bookmyticket.Exception.UserNotFoundException;
import org.jsp.bookmyticket.Exception.UserNotFoundException;
import org.jsp.bookmyticket.dao.AdminDao;
import org.jsp.bookmyticket.dao.UserDao;
import org.jsp.bookmyticket.dto.AdminRequest;
import org.jsp.bookmyticket.dto.AdminResponse;
import org.jsp.bookmyticket.dto.ResponseStructure;
import org.jsp.bookmyticket.dto.UserRequest;
import org.jsp.bookmyticket.dto.UserResponse;
import org.jsp.bookmyticket.model.Admin;
import org.jsp.bookmyticket.model.User;
import org.jsp.bookmyticket.model.User;
import org.jsp.bookmyticket.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserMailService userMailService;
	
	
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest userRequest, HttpServletRequest request) {
		String siteUrl = request.getRequestURL().toString();
		String path = request.getServletPath();
		String activation_link = siteUrl.replace(path, "/api/users/activate");
		ResponseStructure<UserResponse> structure = new ResponseStructure<>();
		String token = RandomString.make(45);
		activation_link += "?token=" + token;
		System.out.println(activation_link);
		User user = mapToUser(userRequest);
		user.setToken(token);
		user.setStatus("IN_Active");
		userDao.saveUser(user);
		structure.setMessage(userMailService.sendMail(user.getEmail(), activation_link));
		structure.setData(mapToUserResponse(user));
		structure.setStatusCode(HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(structure);
	}
	
	public ResponseEntity<ResponseStructure<UserResponse>> update(UserRequest userRequest, int id) {
		Optional<User> recUser = userDao.findById(id);
		ResponseStructure<UserResponse> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			User dbUser = recUser.get();
			dbUser.setEmail(userRequest.getEmail());
			dbUser.setAge(userRequest.getAge());
			dbUser.setGender(userRequest.getGender());
			dbUser.setName(userRequest.getName());
			dbUser.setPhone(userRequest.getPhone());
			dbUser.setPassword(userRequest.getPassword());
			structure.setData(mapToUserResponse(userDao.saveUser(dbUser)));
			structure.setMessage("User Updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
		}
		throw new UserNotFoundException("Cannot Update User as Id is Invalid");
	}
	
	public ResponseEntity<ResponseStructure<UserResponse>> findById(int id) {
		ResponseStructure<UserResponse> structure = new ResponseStructure<>();
		Optional<User> dbUser = userDao.findById(id);
		if (dbUser.isPresent()) {
			structure.setData(mapToUserResponse(dbUser.get()));
			structure.setMessage("User Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new UserNotFoundException("Invalid User Id");
	}

	public ResponseEntity<ResponseStructure<UserResponse>> verify(long phone, String password) {
		ResponseStructure<UserResponse> structure = new ResponseStructure<>();
		Optional<User> dbUser = userDao.verify(phone, password);
		if (dbUser.isPresent()) {
			structure.setData(mapToUserResponse(dbUser.get()));
			structure.setMessage("Verification Succesfull");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new UserNotFoundException("Invalid Phone Number or Password");
	}

	public ResponseEntity<ResponseStructure<UserResponse>> verify(String email, String password) {
		ResponseStructure<UserResponse> structure = new ResponseStructure<>();
		Optional<User> dbUser = userDao.verify(email, password);
		if (dbUser.isPresent()) {
			structure.setData(mapToUserResponse(dbUser.get()));
			structure.setMessage("Verification Succesfull");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new UserNotFoundException("Invalid Email Id or Password");
	}

	public ResponseEntity<ResponseStructure<String>> delete(int id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<User> dbUser = userDao.findById(id);
		if (dbUser.isPresent()) {
			userDao.delete(id);
			structure.setData("User Found");
			structure.setMessage("User deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new UserNotFoundException("Cannot delete User as Id is Invalid");
	}
	
	private User mapToUser(UserRequest userRequest) {
		return User.builder().name(userRequest.getName()).age(userRequest.getAge()).email(userRequest.getEmail())
				.gender(userRequest.getGender()).phone(userRequest.getPhone()).password(userRequest.getPassword()).build();
	}
	
	private UserResponse mapToUserResponse(User user) {
		return UserResponse.builder().name(user.getName()).age(user.getAge()).email(user.getEmail()).gender(user.getGender())
				.phone(user.getPhone()).password(user.getPassword()).id(user.getId()).build();
	}
	
	public String activate(String token) {
		Optional<User> recUser = userDao.findByToken(token);
		if(recUser.isEmpty())
			throw new UserNotFoundException("Invalid Token");
		User dbUser = recUser.get();
		dbUser.setStatus("ACTIVE");
		dbUser.setToken(null);
		userDao.saveUser(dbUser);
		return "Your Account has been activated";
	}
	
	
	
	
	
	
	
	
}
