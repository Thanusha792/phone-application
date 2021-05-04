package com.cg.phonebook.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.phonebook.domain.AdminEntity;
import com.cg.phonebook.domain.UserEntity;
import com.cg.phonebook.exception.AdminException;
import com.cg.phonebook.exception.ResourceNotFoundException;
import com.cg.phonebook.exception.UserValidate;
import com.cg.phonebook.repository.AdminRepository;
import com.cg.phonebook.service.AdminService;
import com.cg.phonebook.service.MapValidationErrorService;
@CrossOrigin
@RestController
@RequestMapping("/api/phonebook")
public class AdminController {
	
	@Autowired
	AdminRepository adminRepo;
	
	@Autowired
	AdminService adminService;
	@Autowired
	MapValidationErrorService mapValidationErrorService;
	
	/*
	 * View all the users in database
	 */
	@GetMapping("/allUsers")
	public Iterable<UserEntity> findAll(){
		Iterable<UserEntity> userList = adminService.findAllUsers();
		if(userList!=null) {
			return adminService.findAllUsers();
		}
		else {
			throw new AdminException("No Contacts available");
		}
	}
	
	/*
	 * View User by identifier
	 */
	@GetMapping("/byUserIdentifier/{userIdentifier}")
	public ResponseEntity<?> getUserByUserIdentifier(@PathVariable String userIdentifier){
		UserEntity user = adminService.findUserByUserIdentifier(userIdentifier);
		if(user !=null) {
			return new ResponseEntity<UserEntity>( user,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>( userIdentifier+" is not available",HttpStatus.BAD_REQUEST);
		}
	}
	
	/*
	 * View user by Phone Number
	 */
	@GetMapping("/byUserphoneNumber/{phoneNumber}")
	public ResponseEntity<?> searchByPhoneNumber(@PathVariable String phoneNumber){
		UserEntity contact = adminService.searchByPhoneNumber(phoneNumber);
		if(contact==null) {
			return new ResponseEntity<String>(phoneNumber+" is not available", HttpStatus.BAD_REQUEST);
		}
		else {
			return new ResponseEntity<UserEntity>(contact, HttpStatus.OK);
		}
	}
	
	/*
	 * Delete User by User ID
	 */
	@DeleteMapping("/byIdentifier/{userIdentifier}")
	public ResponseEntity<?> deleteUserByIdentifier(@PathVariable String userIdentifier){
		boolean deleteUser = adminService.deleteUserByUserIdentifier(userIdentifier.toUpperCase());
		if(deleteUser == true) {
			return new ResponseEntity<String>(userIdentifier+" is deleted", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>(userIdentifier+" is not available", HttpStatus.BAD_REQUEST);
		}	
	}
	
	/*
	 * Delete user by phone Number
	 */
	@DeleteMapping("/{phoneNumber}")
	public ResponseEntity<?> removeUserByPhoneNumber(@PathVariable String phoneNumber){
		boolean deleteUser = adminService.removeUserByPhoneNumber(phoneNumber);
		if(deleteUser == true) {
			return new ResponseEntity<String>(phoneNumber+" is deleted", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>(phoneNumber+" is not available", HttpStatus.BAD_REQUEST);
		}		
	}
	/*
	 * Admin login
	 */
	@GetMapping("/adminLogin/{email}/{password}")
	public String login(@PathVariable("email") String email, @PathVariable("password") String password) {
		Object obj = null;
        obj = adminRepo.loginAdmin(email, password);
		 
		if (obj != null)
			return "Login successfull";
		else
			return "Id or password is incorrect";
	}

	/*
	 * New admin register
	 */
	@PostMapping("/adminregister")
	public ResponseEntity<?> addAdmin(@RequestBody AdminEntity data) throws ResourceNotFoundException {
		System.out.println(data);
		AdminEntity v=adminService.findByMailId(data.getEmail());
		UserValidate validateUser=new UserValidate();
		boolean user=validateUser.validateUser(data.getEmail());
		boolean password1=validateUser.validatePassword(data.getPassword(), data.getEmail());
		
		for(AdminEntity e:adminRepo.findAll()) {
			if(e.getPhoneNumber().equalsIgnoreCase(data.getPhoneNumber())) {
				throw new AdminException("Phone Number already exist");
			}
			if(e.getAdminIdentifier().equalsIgnoreCase(data.getAdminIdentifier())) {
				throw new AdminException("Admin Id already exist!! Please Try another");
			}
		}
		if(user&&password1) {
			if(v==null) {
					if(data.getPassword().equals(data.getConfirmPassword())) {
						AdminEntity admin = adminService.saveAdmin(data);
						return new ResponseEntity<AdminEntity> (admin, HttpStatus.CREATED);
					}
					else {
						throw new ResourceNotFoundException("confirm password must match with password");
					}
			}
			else
				throw new ResourceNotFoundException("Mail Id already exist!!");
			}else {
				throw new ResourceNotFoundException("Enter mailId or password in specified format");
			}
	}
	
}
