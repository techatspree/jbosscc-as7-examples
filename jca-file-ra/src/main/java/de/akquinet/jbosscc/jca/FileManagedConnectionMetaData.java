package de.akquinet.jbosscc.jca;

import java.util.logging.Logger;

import javax.resource.ResourceException;

import javax.resource.spi.ManagedConnectionMetaData;

public class FileManagedConnectionMetaData implements ManagedConnectionMetaData {

	private static final int MAX_CONNECTION = 1;
	private static final String EIS_PRODUCT_NAME = "File";
	private static final String EIS_PRODUCT_VERSION = "1.0";
	private static Logger log = Logger
			.getLogger(FileManagedConnectionMetaData.class.getName());

	public FileManagedConnectionMetaData() {
	}

	/**
	 * Returns Product name of the underlying EIS instance connected through the
	 * ManagedConnection.
	 *
	 * @return Product name of the EIS instance
	 * @throws ResourceException
	 *             Thrown if an error occurs
	 */
	@Override
	public String getEISProductName() throws ResourceException {
		log.finest("getEISProductName()");
		return EIS_PRODUCT_NAME;
	}

	/**
	 * Returns Product version of the underlying EIS instance connected through
	 * the ManagedConnection.
	 *
	 * @return Product version of the EIS instance
	 * @throws ResourceException
	 *             Thrown if an error occurs
	 */
	@Override
	public String getEISProductVersion() throws ResourceException {
		log.finest("getEISProductVersion()");
		return EIS_PRODUCT_VERSION;
	}

	/**
	 * Returns maximum limit on number of active concurrent connections
	 *
	 * @return Maximum limit for number of active concurrent connections
	 * @throws ResourceException
	 *             Thrown if an error occurs
	 */
	@Override
	public int getMaxConnections() throws ResourceException {
		log.finest("getMaxConnections()");
		return MAX_CONNECTION;
	}

	/**
	 * Returns name of the user associated with the ManagedConnection instance
	 *
	 * @return Name of the user
	 * @throws ResourceException
	 *             Thrown if an error occurs
	 */
	@Override
	public String getUserName() throws ResourceException {
		log.finest("getUserName()");
		return null;
	}

}
