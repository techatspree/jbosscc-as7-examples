package de.akquinet.jbosscc.dao;

import javax.ejb.Local;

import de.akquinet.jbosscc.User;
import de.akquinet.jbosscc.dao.common.Dao;

@Local
public interface UserDao extends Dao<User> {

	User findByUsername(String username);

}
