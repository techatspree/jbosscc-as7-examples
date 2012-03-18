package de.akquinet.jbosscc.jca;

import java.util.logging.Logger;

import javax.naming.NamingException;
import javax.naming.Reference;

import javax.resource.ResourceException;
import javax.resource.spi.ConnectionManager;

public class FileConnectionFactoryImpl implements FileConnectionFactory {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger
			.getLogger(FileConnectionFactoryImpl.class.getName());

	private Reference reference;

	private FileManagedConnectionFactory mcf;

	private ConnectionManager connectionManager;

	public FileConnectionFactoryImpl() {
	}

	public FileConnectionFactoryImpl(FileManagedConnectionFactory mcf,
			ConnectionManager cxManager) {
		this.mcf = mcf;
		this.connectionManager = cxManager;
	}

	/**
	 * Get connection from factory
	 *
	 * @return FileConnection instance
	 * @exception ResourceException
	 *                Thrown if a connection can't be obtained
	 */
	@Override
	public FileConnection getConnection() throws ResourceException {
		log.finest("getConnection()");
		return (FileConnection) connectionManager.allocateConnection(mcf, null);
	}

	/**
	 * Get the Reference instance.
	 *
	 * @return Reference instance
	 * @exception NamingException
	 *                Thrown if a reference can't be obtained
	 */
	@Override
	public Reference getReference() throws NamingException {
		log.finest("getReference()");
		return reference;
	}

	/**
	 * Set the Reference instance.
	 *
	 * @param reference
	 *            A Reference instance
	 */
	@Override
	public void setReference(Reference reference) {
		log.finest("setReference()");
		this.reference = reference;
	}

}
