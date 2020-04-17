package com.bmdb.business;

import javax.persistence.*;

@Entity
public class Actor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	private String gender;
	private String birthDate;
	public Actor() {
		super();
	}
	public Actor(int id, String firstname, String lastname, String gender, String birthdate) {
		super();
		this.id = id;
		this.firstName = firstname;
		this.lastName = lastname;
		this.gender = gender;
		this.birthDate = birthdate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstName;
	}
	public void setFirstname(String firstname) {
		this.firstName = firstname;
	}
	public String getLastname() {
		return lastName;
	}
	public void setLastname(String lastname) {
		this.lastName = lastname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthdate() {
		return birthDate;
	}
	public void setBirthdate(String birthdate) {
		this.birthDate = birthdate;
	}
	@Override
	public String toString() {
		return "Actor [id=" + id + ", firstname=" + firstName + ", lastname=" + lastName + ", gender=" + gender
				+ ", birthdate=" + birthDate + "]";
	}
	
}
