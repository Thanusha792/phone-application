package com.cg.phonebook.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.phonebook.domain.Contact;
import com.cg.phonebook.domain.UserEntity;
import com.cg.phonebook.exception.ContactException;
import com.cg.phonebook.repository.ContactRepository;
import com.cg.phonebook.repository.PhonebookUsersRepository;

@Service
public class PhonebookUsersService {
	
	private static final Logger logger = LoggerFactory.getLogger(PhonebookUsersService.class);

	@Autowired
	PhonebookUsersRepository repo;
	@Autowired
	private ContactRepository contactRepository;
	
	
	/*
	 * To register the new User
	 */
	public UserEntity addNewUser(UserEntity user) {
			return repo.save(user);
		
	}
	
	
	public UserEntity addContact(String userIdentifier, Contact contact) {
		
		for(UserEntity c:repo.findAll()) {
			if(c.getUserIdentifier().equalsIgnoreCase(userIdentifier)) {
				Contact con = new Contact();
				for(Contact d : contactRepository.findAll()) {
					if(d.getContactName().equalsIgnoreCase(contact.getContactName()))
					{
						logger.error("Name already exist");
						throw new ContactException("Name already exist");
					}
					if(d.getContactNumber().equals(contact.getContactNumber())) {
						logger.error("Number already exist");
						throw new ContactException("Number already exist");
					}
				}
				con.setContactName(contact.getContactName());
				con.setContactNumber(contact.getContactNumber());
				con.setEmail(contact.getEmail());
				con.setUserId(c.getUserId());
				c.getContactList().add(con);
				logger.info("Contact details added");
				return repo.save(c);
			}
			else {
				logger.error(userIdentifier+" does not exist");
				throw new ContactException(userIdentifier+" does not exist");
			}
		}
		return null;
}
	
	/*
	 * View all the contacts
	 */
	public List<Contact> viewAllContacts(String userIdentifier){
		List<Contact> contactList = new ArrayList<Contact>();
		UserEntity user = repo.findByUserIdentifier(userIdentifier);
		
		long id = user.getUserId();
		for(Contact c : contactRepository.findAll()) {
			if(c.getUserId()==id) {
				contactList.add(c);
			}
		}
		return contactList;
	}
	
	/*
	 * Delete contact by phone number
	 */
	public boolean deleteContact(long userId, String phoneNumber) {
		
		for(Contact c : contactRepository.findAll()) {
			if(c.getUserId()==userId && c.getContactNumber().equals(phoneNumber)) {
				contactRepository.delete(c);
				logger.info("Contact details deleted");
				return true;
				}
		}
		return false;
	}
	
	/*
	 * Find user by mailID
	 */
	public UserEntity findByMailId(String mailId) {
		return contactRepository.findByMailID(mailId);
	}
	
	public Contact findByContactNumber(String number) {
	
	Contact contact = contactRepository.findByContactNumber(number);
	if (contact == null) {
		logger.error(number+ " is not available");
		throw new ContactException(number + " not available");
	}
	return contact;
}

	
//	/*
//	 * Update existing contact
//	 */
//	 public Contact updateContact(Contact contact, String userIdentifier, String name) { 
//		  UserEntity user = repo.findByUserIdentifier(userIdentifier);
//		  long id= user.getUserId();
//		  for(Contact c : contactRepository.findAll()) {
//			  if(c.getUserId()==id) {
//				  c.setContactName(contact.getContactName());
//				  c.setContactNumber(contact.getContactNumber());
//				  c.setEmail(contact.getEmail());
//				  return contactRepository.save(c);
//			  }
//		  }
//		  return null;
//	 }
	/*
	 * To save the new contact
	 */
//	public UserEntity saveContacts(String userIdentifier, Contact contact) {
//		
//		UserEntity user = repo.findByUserIdentifier(userIdentifier);
//			user.getContactList().add(contact);
//			logger.info("Contact added");
//			return repo.save(user);
//		
//	}
	/*
	 * Find contact by phone number
	 */

}
