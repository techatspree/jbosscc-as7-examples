package de.akquinet.jbosscc.asynchronous;

import java.util.Date;
import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless(name="asynchronousService")
public class AsynchronousServiceBean implements AsynchronousService {

	private final static Logger LOG =LoggerFactory.getLogger(AsynchronousServiceBean.class);

	@Asynchronous
	public void async(){
		LOG.info("invoke asynchronous");
	}

	@Asynchronous
	public Future<String> asyncResult(){
		long now = new Date().getTime();

		while (new Date().getTime() < now + 1000) {
			LOG.info("...");
		}

		return new AsyncResult<String>("Hello EJB!");
	}
}
