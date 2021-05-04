package com.cg.phonebook.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usersupport {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long Id;
	/*
	 * User support Id
	 */
	@Column(unique=true,updatable=false)
	private String supportIdentifier;
	/*
	 * User support name
	 */
	private String name;
	
	public Usersupport(String supportId, String name) {
		super();
		this.name = name;
	}
	public Usersupport() {
		super();
	}
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getSupportIdentifier() {
		return supportIdentifier;
	}
	public void setSupportId(String supportIdentifier) {
		this.supportIdentifier = supportIdentifier;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Usersupport [Id=" + Id + ", supportIdentifier=" + supportIdentifier + ", name=" + name + "]";
	}
	

}	