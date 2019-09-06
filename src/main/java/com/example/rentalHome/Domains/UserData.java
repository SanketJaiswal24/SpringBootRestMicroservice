package com.example.rentalHome.Domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Email;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;


@Entity
@ApiModel(description="This Resource Model Delegate To UserData")
public class UserData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "The Auto Generated User Id")
	private Long userId;

	@Size(min=12, message="Name should have atleast 12 characters")
	@ApiModelProperty(notes = "User Name")
	private String name;

	@Size(max=10, message="Mobile No should have atleast 10 characters")
	@ApiModelProperty(notes = "User Contact")
    private String contact;

	@Email(message = "Please Insert Valid Email Id")
	@Column(name = "email_id")
	@ApiModelProperty(notes = "User EmailId")
    private String email;

	@ApiModelProperty(notes = "User Address")
    private String address;

	@ApiModelProperty(notes = "User DOB")
    private String dob;

	@ApiModelProperty(notes = "User Gender")
    private String gender;

	@ApiModelProperty(notes = "User ProfilePicture")
    private String profilePicture;

	public UserData() {
		super();
	}

	public UserData(Long userId, String name, String contact, String email, String address, String dob, String gender,
			String profilePicture) {
		super();
		this.userId = userId;
		this.name = name;
		this.contact = contact;
		this.email = email;
		this.address = address;
		this.dob = dob;
		this.gender = gender;
		this.profilePicture = profilePicture;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
}

