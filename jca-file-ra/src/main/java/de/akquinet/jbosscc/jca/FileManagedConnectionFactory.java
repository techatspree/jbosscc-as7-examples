package de.akquinet.jbosscc.jca;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

import javax.resource.ResourceException;
import javax.resource.spi.ConnectionDefinition;
import javax.resource.spi.ConnectionManager;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionFactory;
import javax.security.auth.Subject;

@ConnectionDefinition(connectionFactory = FileConnectionFactory.class, connectionFactoryImpl = FileConnectionFactoryImpl.class, connection = FileConnection.class, connectionImpl = FileConnectionImpl.class)
public class FileManagedConnectionFactory implements ManagedConnectionFactory {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger
			.getLogger(FileManagedConnectionFactory.class.getName());

	private PrintWriter logwriter;

	public FileManagedConnectionFactory() {

	}

	/**
	 * Creates a Connection Factory instance.
	 *
	 * @param cxManager
	 *            ConnectionManager to be associated with created EIS connection
	 *            factory instance
	 * @return EIS-specific Connection Factory instance or
	 *         javax.resource.cci.ConnectionFactory instance
	 * @throws ResourceException
	 *             Generic exception
	 */
	public Object createConnectionFactory(ConnectionManager cxManager)
			throws ResourceException {
		log.finest("createConnectionFactory()");
		return new FileConnectionFactoryImpl(this, cxManager);
	}

	/**
	 * Creates a Connection Factory instance.
	 *
	 * @return EIS-specific Connection Factory instance or
	 *         javax.resource.cci.ConnectionFactory instance
	 * @throws ResourceException
	 *             Generic exception
	 */
	public Object createConnectionFactory() throws ResourceException {
		throw new ResourceException(
				"This resource adapter doesn't support non-managed environments");
	}

	/**
	 * Creates a new physical connection to the underlying EIS resource manager.
	 *
	 * @param subject
	 *            Caller's security information
	 * @param cxRequestInfo
	 *            Additional resource adapter specific connection request
	 *            information
	 * @throws ResourceException
	 *             generic exception
	 * @return ManagedConnection instance
	 */
	public ManagedConnection createManagedConnection(Subject subject,
			ConnectionRequestInfo cxRequestInfo) throws ResourceException {
		log.finest("createManagedConnection()");
		return new FileManagedConnection(this);
	}

	/**
	 * Returns a matched connection from the candidate set of connections.
	 *
	 * @param connectionSet
	 *            Candidate connection set
	 * @param subject
	 *            Caller's security information
	 * @param cxRequestInfo
	 *            Additional resource adapter specific connection request
	 *            information
	 * @throws ResourceException
	 *             generic exception
	 * @return ManagedConnection if resource adapter finds an acceptable match
	 *         otherwise null
	 */
	@Override
	@SuppressWarnings("unchecked")
	public ManagedConnection matchManagedConnections(Set connectionSet,
			Subject subject, ConnectionRequestInfo cxRequestInfo)
			throws ResourceException {
		log.finest("matchManagedConnections()");
		ManagedConnection result = null;

		Iterator<ManagedConnection> it = connectionSet.iterator();
		while (result == null && it.hasNext()) {
			ManagedConnection mc = it.next();
			if (mc instanceof FileManagedConnection) {
				result = mc;
			}

		}
		return result;
	}

	/**
	 * Get the log writer for this ManagedConnectionFactory instance.
	 *
	 * @return PrintWriter
	 * @throws ResourceException
	 *             generic exception
	 */
	public PrintWriter getLogWriter() throws ResourceException {
		log.finest("getLogWriter()");
		return logwriter;
	}

	/**
	 * Set the log writer for this ManagedConnectionFactory instance.
	 *
	 * @param out
	 *            PrintWriter - an out stream for error logging and tracing
	 * @throws ResourceException
	 *             generic exception
	 */
	public void setLogWriter(PrintWriter out) throws ResourceException {
		log.finest("setLogWriter()");
		logwriter = out;
	}

	/**
	 * Returns a hash code value for the object.
	 *
	 * @return A hash code value for this object.
	 */
	@Override
	public int hashCode() {
		int result = 17;
		return result;
	}

	/**
	 * Indicates whether some other object is equal to this one.
	 *
	 * @param other
	 *            The reference object with which to compare.
	 * @return true if this object is the same as the obj argument, false
	 *         otherwise.
	 */
	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (other == this)
			return true;
		if (!(other instanceof FileManagedConnectionFactory))
			return false;
		boolean result = true;
		return result;
	}

}
