package de.akquinet.jbosscc;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import de.akquinet.jbosscc.annotation.CurrentUser;
import de.akquinet.jbosscc.dao.BlogEntryDao;

@Named
@ConversationScoped
public class BlogEntryService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger log;

	@Inject
	private BlogEntryDao blogEntryDao;

	@Inject
	private Conversation conversation;

	@Inject
	@CurrentUser
	private User user;

	private Long id;

	private BlogEntry instance;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		begin();

		log.info("set blogentry id " + id);
		this.id = id;
	}

	@Produces
	public BlogEntry getInstance() {
		if (instance == null || (id != null && id != instance.getId())) {
			instance = blogEntryDao.find(id);
		}
		log.info("return blog entry " + instance);
		return instance;
	}

	public boolean newInstance() {
		begin();
		BlogEntry blogEntry = new BlogEntry();
		blogEntry.setAuthor(user);
		instance = blogEntry;
		id = null;
		return true;
	}

	private void begin() {
		if (conversation.isTransient()) {
			conversation.begin();
			log.info("begin conversation with id " + conversation.getId());
		}
	}

	private void end() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
	}

	public boolean persistOrUpdate() {
		if (instance.getId() == null) {
			blogEntryDao.persist(instance);
		} else {
			instance = blogEntryDao.merge(instance);
		}
		id = instance.getId();
		return true;
	}

	public boolean delete() {
		log.info("delete blog entry " + instance);
		blogEntryDao.remove(instance);

		end();
		return true;
	}


	public BlogEntryDao get(){
		return blogEntryDao;
	}

}
