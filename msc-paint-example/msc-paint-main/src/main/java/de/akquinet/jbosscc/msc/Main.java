package de.akquinet.jbosscc.msc;

import java.util.HashSet;
import java.util.ServiceLoader;
import java.util.Set;

import org.jboss.modules.Module;
import org.jboss.modules.ModuleIdentifier;
import org.jboss.modules.ModuleLoadException;
import org.jboss.modules.ModuleLoader;
import org.jboss.msc.service.AbstractServiceListener;
import org.jboss.msc.service.ServiceBuilder;
import org.jboss.msc.service.ServiceBuilder.DependencyType;
import org.jboss.msc.service.ServiceContainer;
import org.jboss.msc.service.ServiceController;
import org.jboss.msc.service.ServiceController.Mode;
import org.jboss.msc.service.ServiceController.Transition;
import org.jboss.msc.service.ServiceListener.Inheritance;
import org.jboss.msc.service.ServiceName;
import org.jboss.msc.service.ServiceTarget;

public class Main {

	private static final String MAIN_MODULE = "de.akquinet.jbosscc.msc-paint-main";

	private final ServiceContainer container = ServiceContainer.Factory
			.create();

	public static void main(String[] args) throws Exception {

		final Main main = new Main();

		main.installPaintFrameService();

		main.awaitTermination();

	}

	private void installPaintFrameService() throws ModuleLoadException {

		PaintFrameService service = new PaintFrameService();
		ServiceTarget serviceSubTarget = container.subTarget();
		ServiceBuilder<PaintFrame> serviceBuilder = serviceSubTarget
				.addService(PaintFrameService.SERVICE_NAME, service);

		serviceBuilder.setInitialMode(Mode.ACTIVE);

		Set<ServiceName> dependencies = installShapeExtensions(
				serviceSubTarget.subTarget(), loadShapeExtensions(MAIN_MODULE));

		for (ServiceName serviceName : dependencies) {
			serviceBuilder.addDependency(DependencyType.OPTIONAL, serviceName,
					service.getShapeInjector(serviceName.getSimpleName()));
		}

		serviceBuilder.addListener(new AbstractServiceListener<PaintFrame>() {

			@Override
			public void transition(
					ServiceController<? extends PaintFrame> controller,
					Transition transition) {
				switch (transition) {
				case REMOVING_to_REMOVED:
					container.shutdown();
					break;
				default:
					break;
				}

			}
		});

		serviceBuilder.install();

	}

	private Set<ServiceName> installShapeExtensions(
			ServiceTarget serviceTarget,
			@SuppressWarnings("rawtypes") ServiceLoader<ShapeExtension> extensions) {
		final Set<ServiceName> serviceNames = new HashSet<ServiceName>();
		for (ShapeExtension<?> shapeExtension : extensions) {
			ServiceBuilder<?> serviceBuilder = shapeExtension.create(
					serviceTarget, PaintFrameService.SERVICE_NAME);

			serviceBuilder.setInitialMode(Mode.ON_DEMAND);
			ServiceController<?> serviceController = serviceBuilder.install();
			serviceNames.add(serviceController.getName());

		}

		return serviceNames;
	}

	@SuppressWarnings("rawtypes")
	private ServiceLoader<ShapeExtension> loadShapeExtensions(
			final String moduleName) throws ModuleLoadException {
		ModuleLoader moduleLoader = ModuleLoader.forClass(Main.class);

		Module loadModule = moduleLoader.loadModule(ModuleIdentifier
				.create(moduleName));

		return loadModule.loadService(ShapeExtension.class);

	}

	private void awaitTermination() throws InterruptedException {
		container.awaitTermination();
		System.exit(1);
	}
}
