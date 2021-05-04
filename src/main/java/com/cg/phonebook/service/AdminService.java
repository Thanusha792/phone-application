package com.cg.phonebook.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.phonebook.domain.AdminEntity;
import com.cg.phonebook.domain.UserEntity;
import com.cg.phonebook.exception.PhonebookUsersException;
import com.cg.phonebook.repository.AdminRepository;
import com.cg.phonebook.repository.PhonebookUsersRepository;

@Service
public class AdminService {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminService.class);
	
	@Autowired
	AdminRepository adminRepo;
	@Autowired
	PhonebookUsersRepository userRepo;
	
	/*
	 * Saving Admin details in repository
	 */
	public AdminEntity saveAdmin(AdminEntity data) {
		return adminRepo.save(data);
	}
	
	/*
	 * Fetching all users in the repository
	 */
	public Iterable<UserEntity> findAllUsers(){
		return userRepo.findAll();
	}
	
	/*
	 * Method to search the user by his ID
	 */
	public UserEntity findUserByUserIdentifier(String userIdentifier) {
		UserEntity user = userRepo.findByUserIdentifier(userIdentifier.toUpperCase());
		if (user == null) {
			logger.error("User Identifier "+userIdentifier+" not available");
			throw new PhonebookUsersException("User ID " + userIdentifier + " not available");
			
		}
		logger.info(userIdentifier+" is found");
		return user;

	}
	
	/*
	 * Method to search user by phone number
	 */
	public UserEntity searchByPhoneNumber(String phoneNumber){
		UserEntity contact = null;
		for(UserEntity contacts:userRepo.findAll()) {
			if(contacts.getPhoneNumber().equalsIgnoreCase(phoneNumber)) {
				logger.info("Details found with phone number : "+phoneNumber);
				contact=contacts;
			}
		}
		if(contact==null) {
			logger.error("Details not found");
		}
		return contact;
	}
	
	/*
	 * Delete the user by his ID
	 */
	public boolean deleteUserByUserIdentifier(String userIdentifier) {
		UserEntity user = userRepo.findByUserIdentifier(userIdentifier.toUpperCase());
		System.out.println(user);
		if(user!=null) {
			logger.info("User details deleted");
			userRepo.delete(user);
			return true;
		}
		else {
			logger.error(userIdentifier+" is not available");
		}
		return false;
	}
	/*
	 * Delete user by Phone Number
	 */
	public boolean removeUserByPhoneNumber(String phoneNumber) {
		boolean flag=false;
		for(UserEntity c:userRepo.findAll()) {
			if((c.getPhoneNumber()).equalsIgnoreCase(phoneNumber)){
				logger.info(phoneNumber+" is deleted");
				userRepo.delete(c);
				flag=true;
			}
		}
		if(flag == false) {
			logger.error(phoneNumber+" is not available");
		}
		return flag;
	}
	/*
	 * Find the admin by mail ID
	 */
	
	public AdminEntity findByMailId(String mailId) {
		return adminRepo.findByMailID(mailId);
	}
	
}
