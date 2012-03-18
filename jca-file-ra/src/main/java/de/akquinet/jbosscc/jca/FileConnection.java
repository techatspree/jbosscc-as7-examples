package de.akquinet.jbosscc.jca;

import java.io.IOException;

import javax.resource.cci.LocalTransaction;

public interface FileConnection extends LocalTransaction {

	public void close();

	void writeFile(String name, byte[] data) throws IOException;
}
