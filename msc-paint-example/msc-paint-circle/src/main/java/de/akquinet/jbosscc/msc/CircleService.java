package de.akquinet.jbosscc.msc;

import org.jboss.msc.service.Service;
import org.jboss.msc.service.ServiceName;
import org.jboss.msc.service.StartContext;
import org.jboss.msc.service.StartException;
import org.jboss.msc.service.StopContext;

public class CircleService implements Service<SimpleShape> {

	public static final ServiceName SERVICE_NAME = ServiceName
			.of("CIRCLE_SHAPE");

	private Circle circle;

	@Override
	public Circle getValue() throws IllegalStateException,
			IllegalArgumentException {
		return circle;
	}

	@Override
	public void start(StartContext context) throws StartException {
		System.out.println("start service " + SERVICE_NAME);
		circle = new Circle();
	}

	@Override
	public void stop(StopContext context) {
		System.out.println("stop service " + SERVICE_NAME);
		circle = null;
	}

}
