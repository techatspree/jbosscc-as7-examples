package de.akquinet.jbosscc.jca.file;

import java.io.File;
import java.io.IOException;

import javax.resource.ResourceException;

import org.apache.commons.io.FileUtils;

public class WriteFileOperation implements FileOperation {

	private File tempFile;
	private String name;

	@Override
	public void begin() throws ResourceException {
	}

	public void write(final String name, final byte[] data) throws IOException {
		this.name = name;
		tempFile = File.createTempFile(name, "tmp");
		FileUtils.writeByteArrayToFile(tempFile, data);
	}

	@Override
	public void commit() throws ResourceException {

		try {
			FileUtils.moveFile(tempFile, new File(name));
		} catch (IOException e) {
			throw new ResourceException(e);
		}

	}

	@Override
	public void rollback() throws ResourceException {
		if (tempFile != null) {
			tempFile.delete();
		}
	}

}
