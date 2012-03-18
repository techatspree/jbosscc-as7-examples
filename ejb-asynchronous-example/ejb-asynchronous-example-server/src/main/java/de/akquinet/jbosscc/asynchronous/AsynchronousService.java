package de.akquinet.jbosscc.asynchronous;

import java.util.concurrent.Future;

import javax.ejb.Remote;

@Remote
public interface AsynchronousService {

	void async();

	Future<String> asyncResult();

}
