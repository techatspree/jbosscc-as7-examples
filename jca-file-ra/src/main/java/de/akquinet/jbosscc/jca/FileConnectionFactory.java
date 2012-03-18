package de.akquinet.jbosscc.jca;

import java.io.Serializable;

import javax.resource.Referenceable;
import javax.resource.ResourceException;

public interface FileConnectionFactory extends Serializable, Referenceable {
	/**
	 * Get connection from factory
	 *
	 * @return FileConnection instance
	 * @exception ResourceException
	 *                Thrown if a connection can't be obtained
	 */
	public FileConnection getConnection() throws ResourceException;

}
