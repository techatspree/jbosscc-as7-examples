package de.akquinet.jbosscc;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.akquinet.jbosscc.dao.BlogEntryDao;
import de.akquinet.jbosscc.dao.CommentDao;

@Stateless
public class CommentTestdata {

	private static final String CONTENT = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat.";

	private static final int QUANTITY = 2;

	@EJB
	private BlogEntryDao blogEntryDao;

	@EJB
	private CommentDao commentDao;

	public void insert() {
		List<BlogEntry> blogEntries = blogEntryDao.findAll();

		for (BlogEntry blogEntry : blogEntries) {
			for (int i = 0; i < QUANTITY; i++) {
				Comment comment = new Comment();
				comment.setAuthor(blogEntry.getAuthor());
				comment.setBlogEntry(blogEntry);
				comment.setContent(CONTENT);

				commentDao.persist(comment);
			}
		}
	}

}
