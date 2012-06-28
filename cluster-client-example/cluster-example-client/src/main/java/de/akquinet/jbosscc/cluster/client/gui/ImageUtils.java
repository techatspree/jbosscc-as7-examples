package de.akquinet.jbosscc.cluster.client.gui;

import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Svetlin Nakov
 */
public class ImageUtils {
	
	private static final int BUF_SIZE = 4096;

	public static Image loadImageFromResource(String resourceLocation,
			Component imageOwner) {
		InputStream inputStream = ImageUtils.class.getClassLoader()
				.getResourceAsStream(resourceLocation);
		try {
			byte[] imageBinaryData = readStreamToEnd(inputStream);
			Image image = Toolkit.getDefaultToolkit().createImage(imageBinaryData);
			ImageUtils.ensureImageIsLoaded(image, imageOwner);
			return image;
		} catch (Exception ex) {
			throw new RuntimeException(
				"Cannot load image from resource: " + resourceLocation, ex);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				// Ignore IO exceptions during file closing
			}
		}
	}

	private static byte[] readStreamToEnd(InputStream inputStream) 
	throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream(BUF_SIZE);
		byte[] buf = new byte[BUF_SIZE];
		while (true) {
			int bytesRead = inputStream.read(buf);
			if (bytesRead == -1) {
				// End of stream reached
				break;
			}
			output.write(buf, 0, bytesRead);
		}
		byte[] streamData = output.toByteArray();
		return streamData;
	}

	public static void ensureImageIsLoaded(Image image, Component imageOwner) {
		MediaTracker mediaTracker = new MediaTracker(imageOwner);
		mediaTracker.addImage(image, 0);
		try {
			mediaTracker.waitForAll();
		} catch (InterruptedException intEx) {
			throw new RuntimeException("Image loading was interrupted.", intEx);
		}
	}

}
