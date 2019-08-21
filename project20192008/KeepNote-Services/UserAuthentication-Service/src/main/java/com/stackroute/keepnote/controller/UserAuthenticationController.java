package com.stackroute.keepnote.controller;

import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.keepnote.exception.UserAlreadyExistsException;
import com.stackroute.keepnote.model.User;
import com.stackroute.keepnote.service.UserAuthenticationService;
import com.stackroute.keepnote.vo.Register;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/*
 * As in this assignment, we are working on creating RESTful web service, hence annotate
 * the class with @RestController annotation. A class annotated with the @Controller annotation
 * has handler methods which return a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized 
 * format. Starting from Spring 4 and above, we can use @RestController annotation which 
 * is equivalent to using @Controller and @ResposeBody annotation
 */
@RestController
@CrossOrigin("*")
public class UserAuthenticationController {

	/*
	 * Autowiring should be implemented for the UserAuthenticationService. (Use
	 * Constructor-based autowiring) Please note that we should not create an object
	 * using the new keyword
	 */
	@Autowired
	UserAuthenticationService authicationService;

	public UserAuthenticationController(UserAuthenticationService authicationService) {
		this.authicationService = authicationService;
	}

	/*
	 * Define a handler method which will create a specific user by reading the
	 * Serialized object from request body and save the user details in the
	 * database. This handler method should return any one of the status messages
	 * basis on different situations: 1. 201(CREATED) - If the user created
	 * successfully. 2. 409(CONFLICT) - If the userId conflicts with any existing
	 * user
	 * 
	 * This handler method should map to the URL "/api/v1/auth/register" using HTTP
	 * POST method
	 */
	@PostMapping("/api/v1/auth/register")
	public ResponseEntity<?> register(@RequestBody User user) {

		try {

			user.setUserAddedDate(LocalDateTime.now());
			authicationService.saveUser(user);

			return new ResponseEntity<User>(user, HttpStatus.CREATED);

		} catch (UserAlreadyExistsException exe) {

			return new ResponseEntity<String>("User Already Exists...", HttpStatus.CONFLICT);

		}
	}

	/*
	 * Define a handler method which will authenticate a user by reading the
	 * Serialized user object from request body containing the username and
	 * password. The username and password should be validated before proceeding
	 * ahead with JWT token generation. The user credentials will be validated
	 * against the database entries. The error should be return if validation is not
	 * successful. If credentials are validated successfully, then JWT token will be
	 * generated. The token should be returned back to the caller along with the API
	 * response. This handler method should return any one of the status messages
	 * basis on different situations: 1. 200(OK) - If login is successful 2.
	 * 401(UNAUTHORIZED) - If login is not successful
	 * 
	 * This handler method should map to the URL "/api/v1/auth/login" using HTTP
	 * POST method
	 */
	@PostMapping("/api/auth/v1")
	public ResponseEntity<?> login(@RequestBody Register register) {

		try {

			User userObj = authicationService.findByUserIdAndPassword(register.getUsername(), register.getPassword());

			if(null != userObj && userObj.getUserPassword().equals(register.getPassword())) {
				return new ResponseEntity<String>(getToken(register.getUsername(), register.getPassword()), HttpStatus.CREATED);
			} else {
				return new ResponseEntity<String>("Invalid User Id or Password.", HttpStatus.CONFLICT);
			}
			

		} catch (Exception exe) {

			return new ResponseEntity<String>("Invalid User Id or Password.", HttpStatus.CONFLICT);

		}
	}
	@PostMapping("/auth/v1/isAuthenticated")
	public ResponseEntity<?> isAuthenticated(HttpServletRequest req, HttpServletResponse res) {

		try {
			final String authHeader = req.getHeader("authorization");
			
			final String token = authHeader.substring(7);
			
			System.out.println("token : " + token);
			
			return new ResponseEntity<Boolean>(true, HttpStatus.ACCEPTED);

		} catch (Exception exe) {

			return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);

		}
	}
// Generate JWT token
	public String getToken(String username, String password) throws Exception {

		return Jwts.builder().setSubject(username).claim("userRole", "username").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey").compact();

	}

}
