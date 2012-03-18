package de.akquinet.jbosscc.jca;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.resource.ResourceException;

import de.akquinet.jbosscc.jca.file.FileOperation;
import de.akquinet.jbosscc.jca.file.WriteFileOperation;

public class FileConnectionImpl implements FileConnection {
	private static Logger LOG = Logger.getLogger(FileConnectionImpl.class
			.getName());

	private FileManagedConnection mc;

	private List<FileOperation> operations = new ArrayList<FileOperation>();

	/**
	 * Default constructor
	 *
	 * @param mc
	 *            FileManagedConnection
	 * @param mcf
	 *            FileManagedConnectionFactory
	 */
	public FileConnectionImpl(FileManagedConnection mc,
			FileManagedConnectionFactory mcf) {
		this.mc = mc;
	}

	public InputStream readFile(final String filename) {
		return null;
	}

	public List<String> listFiles() {
		return null;
	}

	@Override
	public void writeFile(String name, byte[] data) throws IOException {
		LOG.info("write file " + name);
		WriteFileOperation writeFileOperation = new WriteFileOperation();
		writeFileOperation.write(name, data);
		operations.add(writeFileOperation);
	}

	@Override
	public void close() {
		mc.closeHandle(this);
	}

	@Override
	public void begin() throws ResourceException {
		LOG.info("beginn");
	}

	@Override
	public void commit() throws ResourceException {
		LOG.info("commit");
		for (FileOperation operation : operations) {
			operation.commit();
		}

		operations.clear();

	}

	@Override
	public void rollback() throws ResourceException {
		LOG.info("rollback");
		for (FileOperation operation : operations) {
			operation.rollback();
		}

		operations.clear();

	}

}
