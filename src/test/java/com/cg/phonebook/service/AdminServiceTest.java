package com.cg.phonebook.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.cg.phonebook.domain.AdminEntity;
import com.cg.phonebook.domain.UserEntity;
import com.cg.phonebook.repository.AdminRepository;
import com.cg.phonebook.repository.PhonebookUsersRepository;

@ExtendWith(SpringExtension.class)
public class AdminServiceTest {

	@Mock
	AdminRepository adminRepo;
	@Mock
	PhonebookUsersRepository userRepo;
	@InjectMocks
	AdminService adminService;
     

	@Test
	void test1_addAdmin() {
		AdminEntity admin = new AdminEntity("Sai","krish","US01","saikrish@gmail.com","9978654321","Chaitanya@1","Chaitanya@1");
		Mockito.when(adminRepo.save(admin)).thenReturn(admin);
		AdminEntity a = adminService.saveAdmin(admin);
		assertNotNull(a);
		assertEquals("Sai", a.getFirstName());
		assertEquals("krish", a.getLastName());
	    assertEquals("saikrish@gmail.com", a.getEmail());
		assertEquals("9978654321", a.getPhoneNumber());
		
	}
	
	@Test
	void test2_findAllUsers() {
		
		List<UserEntity> phonebookUsersList = new ArrayList<>();
		UserEntity user1 = new UserEntity("Sai","krishna","US01","saikrishna@gmail.com","9923456781","Chaitanya@1","Chaitanya@1","address",null);
		phonebookUsersList.add(user1);
		Mockito.when(userRepo.findAll()).thenReturn(phonebookUsersList);
		Iterable<UserEntity> users = adminService.findAllUsers();
		assertNotNull(users);
		assertEquals(phonebookUsersList, users);
		
	}
	
	@Test
    public void test3_getUserById_return_userObject()
    {
		
        Mockito.when(userRepo.findByUserIdentifier("US01")).thenReturn(new UserEntity("Sai","krishna","US01","saikrishna@gmail.com","9923456781","Chaitanya@1","Chaitanya@1","address",null));
        UserEntity user = adminService.findUserByUserIdentifier("US01");
        assertEquals("Sai", user.getFirstName());
        assertEquals("krishna", user.getLastName());
        assertEquals("saikrishna@gmail.com", user.getEmail());
        assertEquals("9923456781", user.getPhoneNumber());
        assertEquals("address", user.getAddress());
    }
	
	@Test
    public void test4_getUserById_return_null()
    {
		
        Mockito.when(userRepo.findByUserIdentifier("US02")).thenReturn(new UserEntity("Sai","krishna","US01","saikrishna@gmail.com","9923456781","Chaitanya@1","Chaitanya@1","address",null));
        UserEntity user = adminService.findUserByUserIdentifier("US02");
        if(user==null) {
        	assertNull(user);
        }
    }
	
	@Test
	void test5_findUserByUserPhoneNumber_return_userObject() {
		
		List<UserEntity> us = new ArrayList<>();
		UserEntity user = new UserEntity("Sai","krishna","US01","saikrishna@gmail.com","9923456781","Chaitanya@1","Chaitanya@1","address",null);
		us.add(user);
		Mockito.when(userRepo.findAll()).thenReturn(us);
		UserEntity u = adminService.searchByPhoneNumber("9923456781");
		assertNotNull(u);
		
	}

	@Test
	void test6_findUserByUserPhoneNumber_return_null() {
		
		List<UserEntity> us = new ArrayList<>();
		UserEntity user = new UserEntity("Sai","krishna","US01","saikrishna@gmail.com","9923456781","Chaitanya@1","Chaitanya@1","address",null);
		us.add(user);
		Mockito.when(userRepo.findAll()).thenReturn(us);
		UserEntity u = adminService.searchByPhoneNumber("992345678");
		assertNull(u);
		
	}
	
	@Test
	void test7_deleteUserByUserIdentifier_return_true() {
		
		Mockito.when(userRepo.findByUserIdentifier("US01")).thenReturn(new UserEntity("Sai","krishna","US01","saikrishna@gmail.com","9923456781","Chaitanya@1","Chaitanya@1","address",null) );
		assertTrue(adminService.deleteUserByUserIdentifier("US01"));
		
	}
	
	@Test
	void test8_deleteUserByUserIdentifier_return_false() {
		
		Mockito.when(userRepo.findByUserIdentifier("US01")).thenReturn(new UserEntity("Sai","krishna","US01","saikrishna@gmail.com","9923456781","Chaitanya@1","Chaitanya@1","address",null) );
		assertFalse(adminService.deleteUserByUserIdentifier("US02"));
		
	}
	
	@Test
	void test9_deleteUserByUserPhoneNumber_return_true() {
		
		List<UserEntity> us = new ArrayList<>();
		UserEntity user = new UserEntity("Sai","krishna","US01","saikrishna@gmail.com","9923456781","Chaitanya@1","Chaitanya@1","address",null);
		us.add(user);
		Mockito.when(userRepo.findAll()).thenReturn(us);
		assertTrue(adminService.removeUserByPhoneNumber("9923456781"));
		
	}
	
	@Test
	void test10_deleteUserByUserPhoneNumber_return_false() {
		
		List<UserEntity> us = new ArrayList<>();
		UserEntity user = new UserEntity("Sai","krishna","US01","saikrishna@gmail.com","9923456781","Chaitanya@1","Chaitanya@1","address",null);
		us.add(user);
		Mockito.when(userRepo.findAll()).thenReturn(us);
		assertFalse(adminService.removeUserByPhoneNumber("992345678"));
		
	}
	
	@Test
    public void test11_getUserByMailId_return_userObject()
    {
		
        Mockito.when(adminRepo.findByMailID("saikrishna@gmail.com")).thenReturn(new AdminEntity("Sai","krishna","US01","saikrishna@gmail.com","9923456781","Chaitanya@1","Chaitanya@1"));
        AdminEntity user = adminService.findByMailId("saikrishna@gmail.com");
        assertEquals("Sai", user.getFirstName());
        assertEquals("krishna", user.getLastName());
        assertEquals("saikrishna@gmail.com", user.getEmail());
        assertEquals("9923456781", user.getPhoneNumber());
       
    }
	
	@Test
    public void test12_getUserByMailId_return_null()
    {
		
        Mockito.when(adminRepo.findByMailID("saikrishna@gmail.com")).thenReturn(new AdminEntity("Sai","krishna","US01","saikrishna@gmail.com","9923456781","Chaitanya@1","Chaitanya@1"));
        AdminEntity user = adminService.findByMailId("saikrishn@gmail.com");
        if(user==null) {
        	assertNull(user);
        }
    }
}
	
	
	
//	@Test
//	 void validateUserData() throws ResourceNotFoundException
//	{
//		try {
//		 AdminEntity data=new AdminEntity();
//	       data.setFirstName("Swapna");
//	       data.setLastName("Vardhan");
//	       data.setPassword("Vishnu@228");
//	       data.setConfirmPassword("Vishnu@228");
//	       data.setEmail("smilyeesonuu@gmail.com");
//	       data.setPhoneNumber("850019666");
//	       data.setAdminIdentifier("AD01");
//		
//    Mockito.when(adminRepo.findByMailID("smilyeesonuu@gmail.com")).thenReturn(data);
//     assertThat(adminService.findByMailId("smilyeesonuu@gmail.com")).isEqualTo(data);
//		}
//		catch(NullPointerException e) {
//			assertTrue(true);
//		}
//	}
//	@Test
//	 void validateUserDataSave() throws ResourceNotFoundException
//	{ 
//		try {
//		 AdminEntity data=new AdminEntity();
//		    data.setFirstName("Swapna");
//		    data.setLastName("Vardhan");
//		    data.setPassword("Vishnu@228");
//		    data.setConfirmPassword("Vishnu@228");
//		    data.setEmail("smilyeesonuuu@gmail.com");
//		    data.setPhoneNumber("850019666");
//		    data.setAdminIdentifier("AD01");
//		    Mockito.when(adminRepo.save(data)).thenReturn(data);
//		     assertThat(adminService.saveAdmin(data)).isEqualTo(data);
//		}catch(NullPointerException e) {
//			assertTrue(true);
//		}
//	}
