package de.akquinet.jbosscc;

import javax.ejb.Remote;

@Remote
public interface CMTExample {

	void never();

	void mandatory();

	void requiresNew();

	void required();

	void callMandatoryWithoutTransaction();

	void callNeverWithTransaction();

	void callMandatory();

}
