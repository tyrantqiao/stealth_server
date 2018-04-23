package com.tyrantQiao.stealth.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Email(message = "input your email, you will receive a confirmation email")
	@NotEmpty(message = "enter your email")
	@Column(name = "email")
	private String email;

	@NotEmpty(message = "input your name")
	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	@Column(name = "confirmationToken")
	private String confirmationToken;

	@Column(name = "enabled")
	private boolean enabled;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "This is user " + this.getName() + " registered by " + this.getEmail() + ", with confirmationToken " + this.getConfirmationToken() + ". Enabled? " + this.isEnabled();
	}
}
