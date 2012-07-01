package de.akquinet.jbosscc.msc;

import org.jboss.msc.service.ServiceBuilder;
import org.jboss.msc.service.ServiceName;
import org.jboss.msc.service.ServiceTarget;

public class CircleExtension implements ShapeExtension<SimpleShape> {

	@Override
	public ServiceBuilder<SimpleShape> create(ServiceTarget target,
			ServiceName parent) {
		CircleService circleService = new CircleService();

		return target.addService(
				ServiceName.of(parent,
						CircleService.SERVICE_NAME.getSimpleName()),
				circleService);
	}
}
