package de.akquinet.jbosscc.msc;

import org.jboss.msc.service.ServiceBuilder;
import org.jboss.msc.service.ServiceName;
import org.jboss.msc.service.ServiceTarget;

public interface ShapeExtension<V extends SimpleShape> {

	ServiceBuilder<V> create(ServiceTarget target, ServiceName parent);

}
