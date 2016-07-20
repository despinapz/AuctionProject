package Auction_Project.AuctionProject.ws.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Auction_Project.AuctionProject.dao.UserDAO;
import Auction_Project.AuctionProject.dto.user.IdResponse;
import Auction_Project.AuctionProject.dto.user.UserListResponse;
import Auction_Project.AuctionProject.dto.user.UserLoginResponse;
import Auction_Project.AuctionProject.dto.user.UserProfileResponse;
import Auction_Project.AuctionProject.dto.user.UsernameResponse;

@RestController
@RequestMapping("/ws/user")
public class UserController {

	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserLoginResponse getByUsernameAndPassword(@RequestBody User input_user){
		User user = new User();
		UserLoginResponse responseUser = new UserLoginResponse(0, null, false, false);
		try {
			user = userDAO.findByUsernameAndPassword(input_user.getUsername(), input_user.getPassword());
			responseUser = new UserLoginResponse(user.getId(), user.getUsername(), user.getActivation(), user.getSuperuser());
		}
		catch (Exception ex){
			System.out.println(ex.getMessage());
		}
		return responseUser;
	}
	
	@RequestMapping(value = "/register/checkUsername", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Boolean registerCheckUsername( @RequestBody User input_user) {
		Long numOfUsernames = (long) -1;
		try {
			numOfUsernames = userDAO.countByUsername(input_user.getUsername());
		}
		catch (Exception ex){
			System.out.println(ex.getMessage());
		}
		if (numOfUsernames != 0)
			return true;
		return false;
	}
	
	@RequestMapping(value = "/register/checkEmail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Boolean registerCheckEmail( @RequestBody User input_user) {
		Long numOfEmails = (long) -1;
		try {
			numOfEmails = userDAO.countByEmail(input_user.getEmail());
		}
		catch (Exception ex){
			System.out.println(ex.getMessage());
		}
		if (numOfEmails != 0)
			return true;
		return false;
	}		
		
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Boolean registerUser( @RequestBody User input_user) {	
		try {
			userDAO.save(input_user);
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
		return true;
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserListResponse> getUsers() {
		List<User> userList = new ArrayList<User>();
		List<UserListResponse> userResponseList = new ArrayList<UserListResponse>();
		try {
			userList = userDAO.findBySuperuser(false);
			for (Iterator<User> iterator = userList.iterator(); iterator.hasNext();) {
				User user = iterator.next();
				UserListResponse userResponse = new UserListResponse(user.getId(), user.getUsername(), user.getEmail(), user.getName(), user.getSurname());
				userResponseList.add(userResponse);
			}
		}
		catch (Exception ex){
			System.out.println(ex.getMessage());
		}
		return userResponseList;
	}
	
	@RequestMapping(value = "/getProfile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserProfileResponse getUserProfile(@RequestBody User input_user) {
		UserProfileResponse responseUser = new UserProfileResponse(null, null, null, null, null, null, null, null, false, false);
		User user = new User();
		try {
			user = userDAO.findById(input_user.getId());
			responseUser = new UserProfileResponse(user.getUsername(), user.getEmail(), user.getName(), user.getSurname(), user.getAddress(),
												user.getCountry(), user.getTelephone(), user.getTrn(), user.getSuperuser(), user.getActivation());
		}
		catch (Exception ex){
			System.out.println(ex.getMessage());
		}
		return responseUser;
	}
	
	@RequestMapping(value = "/activate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean activateUser(@RequestBody User input_user) {
		User user = new User();
		try {
			user = userDAO.findById(input_user.getId());
			user.setActivation(true);
			userDAO.save(user);
		}
		catch (Exception ex){
			System.out.println(ex.getMessage());
			return false;
		}
		return true;
	}
	
	@RequestMapping(value = "/ban", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean banUser(@RequestBody User input_user) {
		User user = new User();
		try {
			user = userDAO.findById(input_user.getId());
			user.setActivation(false);
			userDAO.save(user);
		}
		catch (Exception ex){
			System.out.println(ex.getMessage());
			return false;
		}
		return true;
	}
	
	@RequestMapping(value = "/getIDbyUsername", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public IdResponse getUserID(@RequestBody UsernameResponse input) {
		User user = new User();
		System.out.println("HELLOOO" + input.getUsername());
		IdResponse responseUser = new IdResponse(0);
		try {
			user = userDAO.findByUsername(input.getUsername());
			responseUser.setId(user.getId());
		}
		catch (Exception ex){
			System.out.println(ex.getMessage());
		}
		System.out.println("HO " + responseUser.getId());
		return responseUser;
	}
	
	@RequestMapping(value = "/updateProfileInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean updateProfileInfo(@RequestBody User input_user) {
		User user = new User();
		try {
			user = userDAO.findById(input_user.getId());
			System.out.println(input_user.getId());
			user.setAddress(input_user.getAddress());
			user.setCountry(input_user.getCountry());
			user.setName(input_user.getName());
			user.setSurname(input_user.getSurname());
			user.setTelephone(input_user.getTelephone());
			userDAO.save(user);
		}
		catch (Exception ex){
			System.out.println(ex.getMessage());
			return false;
		}
		return true;
	}
	
}
