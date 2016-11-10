package com.intelligrape.linkshare.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.intelligrape.linkshare.customValidation.CheckEquality;

@Entity
@Table(name = "user")
@CheckEquality
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String username;
	
	
	@NotEmpty
	private String password;
	
	@NotNull
	@Email
	private String email;
	
	private String firstName;
	
	private String lastName;
	@NotNull
	
	private Boolean admin;
	@NotNull
	
	private Boolean active;
	@Transient
	private String confirmPassword;
	
	@Transient
	private String name;
	@OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Topic> topics = new ArrayList<>();
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Subscription> subscriptions = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {

		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getName() {

		return getFirstName() + " " + getLastName();
	}

	public void addTopics(Topic topic) {
		topics.add(topic);
		topic.setCreatedBy(this);
	}

	public void addSubscriptions(Subscription subscription) {
		subscriptions.add(subscription);
		subscription.setUser(this);
	}
}
