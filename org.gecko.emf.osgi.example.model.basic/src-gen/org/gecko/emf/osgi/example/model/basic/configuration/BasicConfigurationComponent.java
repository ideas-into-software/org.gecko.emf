/*
 */
package org.gecko.emf.osgi.example.model.basic.configuration;

import java.util.Dictionary;
import java.util.Hashtable;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;

import org.gecko.emf.osgi.api.EMFNamespaces;
import org.gecko.emf.osgi.api.EPackageConfigurator;
import org.gecko.emf.osgi.api.ResourceFactoryConfigurator;

import org.gecko.emf.osgi.api.annotation.EMFModel;

import org.gecko.emf.osgi.api.annotation.provide.ProvideEMFModel;
import org.gecko.emf.osgi.api.annotation.provide.ProvideEMFResourceConfigurator;

import org.gecko.emf.osgi.api.annotation.require.RequireEMF;

import org.gecko.emf.osgi.example.model.basic.BasicPackage;

import org.gecko.emf.osgi.example.model.basic.impl.BasicPackageImpl;

import org.gecko.emf.osgi.example.model.basic.util.BasicResourceFactoryImpl;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * <!-- begin-user-doc -->
 * The <b>EPackageConfiguration</b> and <b>ResourceFactoryConfigurator</b> for the model.
 * The package will be registered into a OSGi base model registry.
 * <!-- end-user-doc -->
 * @see EPackageConfigurator
 * @see ResourceFactoryConfigurator
 * @generated
 */
@Component(name="BasicConfigurator", service= {EPackageConfigurator.class, ResourceFactoryConfigurator.class})
@EMFModel(name=BasicPackage.eNAME, nsURI={BasicPackage.eNS_URI}, version="1.0.0")
@RequireEMF
@ProvideEMFModel(name = BasicPackage.eNAME, nsURI = { BasicPackage.eNS_URI }, version = "1.0.0")
@ProvideEMFResourceConfigurator( name = BasicPackage.eNAME,
	contentType = { "basic#1.0" }, 
	fileExtension = {
	"basic"
 	},  
	version = "1.0.0"
)
public class BasicConfigurationComponent implements EPackageConfigurator, ResourceFactoryConfigurator {
	private ServiceRegistration<?> packageRegistration = null;
	
	@Activate
	public void activate(BundleContext ctx) {
		BasicPackage p = BasicPackageImpl.init();
		if(p == null){
			p= BasicPackageImpl.eINSTANCE;
			EPackage.Registry.INSTANCE.put(BasicPackage.eNS_URI,p);
		}
		Dictionary<String, Object> properties = new Hashtable<String, Object>();
		properties.put(EMFNamespaces.EMF_MODEL_NAME, BasicPackage.eNAME);
		properties.put(EMFNamespaces.EMF_MODEL_NSURI, BasicPackage.eNS_URI);
		properties.put(EMFNamespaces.EMF_MODEL_FILE_EXT, "basic");
		properties.put(EMFNamespaces.EMF_MODEL_CONTENT_TYPE, "basic#1.0");
		String[] serviceClasses = new String[] {BasicPackage.class.getName(), EPackage.class.getName()};
		packageRegistration = ctx.registerService(serviceClasses, p, properties);
	}

	/* 
	 * (non-Javadoc)
	 * @see org.gecko.emf.osgi.core.ResourceFactoryConfigurator#configureResourceFactory(org.eclipse.emf.ecore.resource.Resource.Factory.Registry)
	 * @generated
	 */
	@Override
	public void configureResourceFactory(Registry registry) {
		registry.getExtensionToFactoryMap().put("basic", new BasicResourceFactoryImpl()); 
		registry.getContentTypeToFactoryMap().put("basic#1.0", new BasicResourceFactoryImpl()); 
	}
	
	/* 
	 * (non-Javadoc)
	 * @see org.gecko.emf.osgi.core.ResourceFactoryConfigurator#unconfigureResourceFactory(org.eclipse.emf.ecore.resource.Resource.Factory.Registry)
	 * @generated
	 */
	@Override
	public void unconfigureResourceFactory(Registry registry) {
		registry.getExtensionToFactoryMap().remove("basic"); 
		registry.getContentTypeToFactoryMap().remove("basic#1.0"); 
	}
	
	/* 
	 * (non-Javadoc)
	 * @see org.gecko.emf.osgi.core.EPackageRegistryConfigurator#configureEPackage(org.eclipse.emf.ecore.EPackage.Registry)
	 * @generated
	 */
	@Override
	public void configureEPackage(org.eclipse.emf.ecore.EPackage.Registry registry) {
		registry.put(BasicPackage.eNS_URI, BasicPackageImpl.init());
	}
	
	/* 
	 * (non-Javadoc)
	 * @see org.gecko.emf.osgi.core.EPackageRegistryConfigurator#unconfigureEPackage(org.eclipse.emf.ecore.EPackage.Registry)
	 * @generated
	 */
	@Override
	public void unconfigureEPackage(org.eclipse.emf.ecore.EPackage.Registry registry) {
		registry.remove(BasicPackage.eNS_URI);
	}
	
	@Deactivate
	public void deactivate() {
		EPackage.Registry.INSTANCE.remove(BasicPackage.eNS_URI);
		if(packageRegistration != null){
			packageRegistration.unregister();
		}
	}
}
