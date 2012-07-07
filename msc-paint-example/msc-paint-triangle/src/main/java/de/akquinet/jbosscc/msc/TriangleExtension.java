package de.akquinet.jbosscc.msc;

import org.jboss.msc.service.ServiceBuilder;
import org.jboss.msc.service.ServiceName;
import org.jboss.msc.service.ServiceTarget;

public class TriangleExtension implements ShapeExtension<SimpleShape> {

	@Override
	public ServiceBuilder<SimpleShape> create(ServiceTarget target,
			ServiceName parent) {
		TriangleService circleService = new TriangleService();
		return target.addService(
				ServiceName.of(parent,
						TriangleService.SERVICE_NAME.getSimpleName()),
				circleService);
	}

}
