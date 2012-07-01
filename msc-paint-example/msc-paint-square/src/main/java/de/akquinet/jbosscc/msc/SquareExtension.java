package de.akquinet.jbosscc.msc;

import org.jboss.msc.service.ServiceBuilder;
import org.jboss.msc.service.ServiceName;
import org.jboss.msc.service.ServiceTarget;

public class SquareExtension implements ShapeExtension<SimpleShape> {

	@Override
	public ServiceBuilder<SimpleShape> create(ServiceTarget target,
			ServiceName parent) {
		SquareService circleService = new SquareService();
		return target.addService(
				ServiceName.of(parent,
						SquareService.SERVICE_NAME.getSimpleName()),
				circleService);
	}

}
