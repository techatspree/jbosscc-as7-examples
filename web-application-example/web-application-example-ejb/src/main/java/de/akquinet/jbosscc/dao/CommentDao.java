package de.akquinet.jbosscc.dao;

import java.util.List;

import javax.ejb.Local;

import de.akquinet.jbosscc.BlogEntry;
import de.akquinet.jbosscc.Comment;
import de.akquinet.jbosscc.dao.common.Dao;

@Local
public interface CommentDao extends Dao<Comment> {

	List<Comment> findComments(BlogEntry blogEntry);

}
