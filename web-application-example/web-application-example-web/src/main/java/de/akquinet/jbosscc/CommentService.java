package de.akquinet.jbosscc;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.akquinet.jbosscc.annotation.CurrentUser;
import de.akquinet.jbosscc.dao.CommentDao;

@Named
@RequestScoped
public class CommentService {

	@Inject
	private Logger log;

	@Inject
	@CurrentUser
	private User user;

	@Inject
	private BlogEntry blogEntry;

	@Inject
	private CommentDao commentDao;

	private Comment instance;

	public Comment getInstance() {
		if (instance == null) {
			log.info("create new comment instance");
			instance = new Comment();
			instance.setAuthor(user);
			instance.setBlogEntry(blogEntry);
		}

		log.info("return comment instance " + instance);

		return instance;

	}

	public void save() {
		log.info("persist comment " + instance);
		commentDao.persist(instance);

		instance = null;
	}

}
