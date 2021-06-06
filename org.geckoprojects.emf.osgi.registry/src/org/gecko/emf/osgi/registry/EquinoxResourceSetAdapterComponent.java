package org.gecko.emf.osgi.registry;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.ContributorFactoryOSGi;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.emf.ecore.EPackage;
import org.gecko.emf.osgi.EMFNamespaces;
import org.gecko.emf.osgi.EPackageConfigurator;
import org.osgi.annotation.bundle.Requirement;
import org.osgi.framework.Bundle;
import org.osgi.framework.Constants;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

/**
 * Implementation of an adapter between the OSGi service based {@link EPackage} registry and the Equinox
 * based {@link IExtensionRegistry}. All registered services provide their bundleId in the service properties.
 * This component loads the plugin.xml from this bundle and registers it in the {@link IExtensionRegistry} or removed all 
 * {@link IExtensionPoint}  , if the {@link EPackageConfigurator} is unregistered.
 * @author Mark Hoffmann
 * @since 28.06.2017
 */
@Component(name="EquinoxResourceSetAdapter", enabled=true, immediate=true)
@Requirement(namespace = EMFNamespaces.EMF_CONFIGURATOR_NAMESPACE, //
	name = EPackageConfigurator.EMF_CONFIGURATOR_NAME, filter="(name=ecore)")
public class EquinoxResourceSetAdapterComponent {

	private final static Logger logger = Logger.getLogger(EquinoxResourceSetAdapterComponent.class.getName());
	private final Map<Long, IContributor> contributorMap = new ConcurrentHashMap<Long, IContributor>();
	@Reference
	private IExtensionRegistry extensionRegistry;
	private ComponentContext ctx;
	
	/**
	 * Injects {@link EPackageConfigurator}, to register a new {@link EPackage}
	 * @param configurator the {@link EPackageConfigurator} to be registered
	 * @param properties the service properties
	 */
	@Reference(policy=ReferencePolicy.DYNAMIC, cardinality=ReferenceCardinality.MULTIPLE)
	public void addEPackageConfigurator(EPackageConfigurator configurator, Map<String, Object> properties) {
		addToExtensionRegistry(properties);
	}

	/**
	 * Removes a {@link EPackageConfigurator} from the registry and unconfigures it
	 * @param configurator the configurator to be removed
	 * @param properties the service properties
	 */
	public void removeEPackageConfigurator(EPackageConfigurator configurator, Map<String, Object> properties) {
		removeFromExtensionRegistry(properties);
	}

	/**
	 * Called on component activation
	 * @param ctx the component context
	 */
	@Activate
	public void activate(ComponentContext ctx) {
		this.ctx = ctx;
	}
	
	/**
	 * Called on component deactivation
	 */
	@Deactivate
	public void deactivate() {
	}
	
	/**
	 * Adds the plugin.xml of the service bundle to the extension registry
	 * @param serviceProperties the service properties
	 */
	private void addToExtensionRegistry(Map<String, Object> serviceProperties) {
		Long bundleId = (Long) serviceProperties.get(Constants.SERVICE_BUNDLEID);
		if (bundleId == null) {
			logger.severe("Cannot add data to the extension registry, because the properties do not contains a 'service.bundleid' property");
			return;
		}
		Bundle bundle = ctx.getBundleContext().getBundle(bundleId.longValue());
		if (bundle == null) {
			logger.severe(String.format("Cannot add data to the extension registry because the bundle for the 'service.bundleid' %d does not exist", bundleId));
			return;
		}
		if (contributorMap.containsKey(bundleId)) {
			logger.fine(String.format("There was already a plugin.xml for this bundle '%s' registered", bundle.getSymbolicName()));
			return;
		}
		URL pluginXML = bundle.getResource("plugin.xml");
		if (pluginXML == null) {
			logger.severe(String.format("Cannot find a plugin.xml in bundle '%s'", bundle.getSymbolicName()));
			return;
		}
		IContributor contributor = ContributorFactoryOSGi.createContributor(bundle);
		try (InputStream is = pluginXML.openStream()){
			String name = contributor.getName() + "_GeckoEMFEquinoxAdapter";
			boolean result = extensionRegistry.addContribution(is, contributor, false, name, null, null);
			if (result) {
				contributorMap.put(bundleId, contributor);
				logger.info(String.format("Added plugin.xml extension for '%s'", contributor.getName()));
			} else {
				logger.severe(String.format("Did not succeed adding plugin.xml data to the extension registry for '%s'", contributor.getName()));
			}
		} catch (IOException e) {
			logger.log(Level.SEVERE, String.format("Error opening plugin.xml file in bundle '%s'", bundle.getSymbolicName()), e);
			return;
		}
	}
	
	/**
	 * Removes all extension for a contributor from the extension registry
	 * @param serviceProperties the service properties
	 */
	private void removeFromExtensionRegistry(Map<String, Object> serviceProperties) {
		Long bundleId = (Long) serviceProperties.get(Constants.SERVICE_BUNDLEID);
		if (bundleId == null) {
			logger.severe("Cannot remove contributor extension from the extension registry because the properties do not contains a 'service.bundleid' property");
			return;
		}
		IContributor contributor = contributorMap.remove(bundleId);
		// nothing to remove
		if (contributor == null) {
			return;
		}
		IExtensionPoint[] extensionPoints = extensionRegistry.getExtensionPoints(contributor);
		for (IExtensionPoint extensionPoint : extensionPoints) {
			boolean result = extensionRegistry.removeExtensionPoint(extensionPoint, null);
			if (result) {
				logger.info(String.format("Successfully removed extension point '%s' for contributor '%s'", extensionPoint.getLabel(), contributor.getName()));
			} else {
				logger.warning(String.format("Removing extension point '%s' for contributor '%s' was not successful", extensionPoint.getLabel(), contributor.getName()));
			}
		}
	}
	
}
