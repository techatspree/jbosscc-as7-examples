package de.akquinet.jbosscc;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.akquinet.jbosscc.dao.BlogEntryDao;

@Named
@RequestScoped
public class BlogEntryListService {

	private static final int MAX_RESULTS = 5;

	@Inject
	private Logger log;

	@Inject
	private BlogEntryDao blogEntryDao;

	private List<BlogEntry> resultList;

	private int firstResult = 0;

	public List<BlogEntry> getResultList() {
		log.info("load blog entries");
		if (resultList == null) {
			resultList = blogEntryDao.find(MAX_RESULTS, firstResult);
		}
		return resultList;
	}

	public int getNextFirstResult() {
		return firstResult + MAX_RESULTS;
	}

	public int getPreviousFirstResult() {
		return MAX_RESULTS >= firstResult ? 0 : firstResult - MAX_RESULTS;
	}

	public Integer getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(Integer firstResult) {
		log.info("set first result " + firstResult);
		this.firstResult = firstResult;
		this.resultList = null;
	}

	public boolean isPreviousExists() {
		return firstResult > 0;
	}

	public boolean isNextExists() {
		return blogEntryDao.count() > MAX_RESULTS + firstResult;
	}

}
