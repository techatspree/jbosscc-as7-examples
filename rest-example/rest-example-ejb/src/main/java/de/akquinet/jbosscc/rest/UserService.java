package de.akquinet.jbosscc.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import de.akquinet.jbosscc.User;
import de.akquinet.jbosscc.dao.UserDao;

@Stateless
@Path(value = "/user")
public class UserService {

	@EJB
	private UserDao userDao;

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces({ "text/xml", "application/json" })
	public User find(@PathParam("id") long id) {
		return userDao.find(id);
	}

	@GET
	@Produces({ "text/xml", "application/json" })
	public List<User> list() {
		return userDao.findAll();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public void remove(@PathParam("id") long id) {
		User user = userDao.find(id);
		userDao.remove(user);
	}

	@POST
	@Consumes({ "text/xml", "application/json" })
	@Produces({ "text/xml", "application/json" })
	public User update(final User user) {
		User currentUser = userDao.find(user.getId());
		currentUser.setFirstname(user.getFirstname());
		currentUser.setSurname(user.getSurname());
		currentUser.setPassword(user.getPassword());
		return userDao.merge(currentUser);
	}

	@PUT
	@Consumes({ "text/xml", "application/json" })
	public void save(final User user) {
		userDao.persist(user);
	}

}
