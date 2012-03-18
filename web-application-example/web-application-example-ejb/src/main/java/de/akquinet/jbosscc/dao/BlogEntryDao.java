package de.akquinet.jbosscc.dao;

import java.util.List;

import javax.ejb.Local;

import de.akquinet.jbosscc.BlogEntry;
import de.akquinet.jbosscc.dao.common.Dao;

@Local
public interface BlogEntryDao extends Dao<BlogEntry> {

	List<BlogEntry> find(int maxresults, int firstresult);

	Long count();

}
