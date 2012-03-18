package de.akquinet.jbosscc;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;
import org.jasypt.util.password.BasicPasswordEncryptor;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Version
	@Column(nullable = false)
	private Long version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = new BasicPasswordEncryptor().encryptPassword(password);
	}

	public boolean verifyPassword(String password) {
		return new BasicPasswordEncryptor().checkPassword(password,
				this.password);
	}

}
