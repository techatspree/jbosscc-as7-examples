package de.akquinet.jbosscc;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.jasypt.util.password.BasicPasswordEncryptor;

import de.akquinet.jbosscc.common.AbstractEntity;

@Entity
public class User extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Column(nullable = false)
	private String firstname;

	@NotEmpty
	@Column(nullable = false)
	private String surname;

	@NotEmpty
	@Column(nullable = false, unique = true)
	private String username;

	@NotEmpty
	@Column(nullable = false)
	private String password;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = new BasicPasswordEncryptor().encryptPassword(password);
	}

	public boolean verifyPassword(String password) {
		return new BasicPasswordEncryptor().checkPassword(password,
				this.password);
	}

}
