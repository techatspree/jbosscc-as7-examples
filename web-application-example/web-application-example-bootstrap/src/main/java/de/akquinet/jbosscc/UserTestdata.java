package de.akquinet.jbosscc;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.akquinet.jbosscc.dao.UserDao;

@Stateless
public class UserTestdata {

	private static final String[][] DATA = { { "Fred", "Johnson" },
			{ "Donald", "McClure" }, { "Jerry", "Francis" } };
	private static final String PASSWORD = "secret";

	@EJB
	private UserDao userDao;

	public void insert() {
		for (String[] data : DATA) {
			String firstname = data[0];
			String surname = data[1];
			String username = firstname.substring(0, 1) + surname;

			User user = new User();
			user.setFirstname(firstname);
			user.setSurname(surname);
			user.setUsername(username.toLowerCase());
			user.setPassword(PASSWORD);

			userDao.persist(user);
		}
	}

}
