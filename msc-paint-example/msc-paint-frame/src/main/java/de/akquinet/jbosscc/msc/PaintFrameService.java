package de.akquinet.jbosscc.msc;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jboss.msc.inject.Injector;
import org.jboss.msc.inject.MapInjector;
import org.jboss.msc.service.Service;
import org.jboss.msc.service.ServiceController.Mode;
import org.jboss.msc.service.ServiceName;
import org.jboss.msc.service.StartContext;
import org.jboss.msc.service.StartException;
import org.jboss.msc.service.StopContext;

public class PaintFrameService implements Service<PaintFrame> {

	public static final ServiceName SERVICE_NAME = ServiceName
			.of("PAINT_FRAME");

	private Map<String, Object> shapes = new HashMap<String, Object>();

	private PaintFrame frame;

	@Override
	public PaintFrame getValue() throws IllegalStateException,
			IllegalArgumentException {
		return frame;
	}

	@Override
	public synchronized void start(final StartContext context)
			throws StartException {
		System.out.println("start service " + SERVICE_NAME);

		if (frame == null) {
			try {
				SwingUtilities.invokeAndWait(new Runnable() {
					public void run() {
						frame = new PaintFrame();
						frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
						frame.addWindowListener(new WindowAdapter() {
							public void windowClosing(WindowEvent evt) {
								context.getController().setMode(Mode.REMOVE);
								frame.setVisible(false);
							}
						});

						frame.setVisible(true);
					}
				});

			} catch (Exception e) {
				e.printStackTrace();
				throw new StartException(e);

			}
		}

		for (Entry<String, Object> entry : shapes.entrySet()) {

			final SimpleShape shape = (SimpleShape) entry.getValue();
			if (shape != null) {
				frame.addShape(shape);
			}
		}

	}

	@Override
	public void stop(StopContext context) {
		System.out.println("stop service " + SERVICE_NAME);

		for (Entry<String, Object> entry : shapes.entrySet()) {

			final SimpleShape shape = (SimpleShape) entry.getValue();
			if (shape != null) {
				frame.removeShape(shape.getName());
			}
		}

	}

	public Injector<Object> getShapeInjector(String name) {
		return new MapInjector<String, Object>(shapes, name);
	}

}
