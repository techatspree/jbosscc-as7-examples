package de.akquinet.jbosscc.remote;

import javax.ejb.Remote;

@Remote
public interface Calculator {

	int add(int x, int y);

	int subtract(int x, int y);
}
