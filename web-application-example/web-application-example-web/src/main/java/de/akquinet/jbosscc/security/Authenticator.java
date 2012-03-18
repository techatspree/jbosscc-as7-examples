package de.akquinet.jbosscc.security;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import de.akquinet.jbosscc.User;
import de.akquinet.jbosscc.dao.UserDao;

@Named
@RequestScoped
public class Authenticator implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger log;

	@Inject
	private UserDao userDao;

	@Inject
	private HttpSession session;

	@Inject
	private Identity identity;

	private String username;
	private String password;

	public boolean login() {
		User user = userDao.findByUsername(username);

		if (user != null && user.verifyPassword(password)) {
			identity.setLoggedIn(true);
			identity.setUser(user);
			return true;
		}

		return false;
	}

	public boolean logout() {
		log.info("logout current user");
		session.invalidate();

		return true;

	}

	public String getUsername() {
		return username;
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

}
