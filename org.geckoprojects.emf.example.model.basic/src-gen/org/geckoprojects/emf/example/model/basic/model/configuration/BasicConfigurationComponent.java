/*
 */
package org.geckoprojects.emf.example.model.basic.model.configuration;

import java.util.Dictionary;
import java.util.Hashtable;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;

import org.geckoprojects.emf.core.api.EPackageConfigurator;
import org.geckoprojects.emf.core.api.ResourceFactoryConfigurator;

import org.geckoprojects.emf.core.api.annotation.EMFModel;

import org.geckoprojects.emf.core.api.annotation.provide.ProvideEMFModel;
import org.geckoprojects.emf.core.api.annotation.provide.ProvideEMFResourceConfigurator;

import org.geckoprojects.emf.core.api.annotation.require.RequireEMF;

import org.geckoprojects.emf.example.model.basic.model.BasicPackage;

import org.geckoprojects.emf.example.model.basic.model.impl.BasicPackageImpl;

import org.geckoprojects.emf.example.model.basic.model.util.BasicResourceFactoryImpl;

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
@EMFModel(emf_model_name=BasicPackage.eNAME, emf_model_nsURI={BasicPackage.eNS_URI}, emf_model_version="1.0")
@RequireEMF
@ProvideEMFModel(name = BasicPackage.eNAME, nsURI = { BasicPackage.eNS_URI }, version = "1.0" )
@ProvideEMFResourceConfigurator( name = BasicPackage.eNAME,
	contentType = { "basic#1.0" }, 
	fileExtension = {
	"basic"
 	},  
	version = "1.0"
)
public class BasicConfigurationComponent implements EPackageConfigurator, ResourceFactoryConfigurator {
	private ServiceRegistration<?> packageRegistration = null;
	
	@Activate
	public void activate(BundleContext ctx) {
		BasicPackage p = BasicPackageImpl.init();
		Dictionary<String, Object> properties = new Hashtable<String, Object>();
		properties.put("emf.model.name", BasicPackage.eNAME);
		properties.put("emf.model.nsURI", BasicPackage.eNS_URI);
		properties.put("fileExtension", "basic");
		properties.put("contentType", "basic#1.0");
		String[] serviceClasses = new String[] {BasicPackage.class.getName(), EPackage.class.getName()};
		packageRegistration = ctx.registerService(serviceClasses, p, properties);
	}

	/* 
	 * (non-Javadoc)
	 * @see org.geckoprojects.emf.core.ResourceFactoryConfigurator#configureResourceFactory(org.eclipse.emf.ecore.resource.Resource.Factory.Registry)
	 * @generated
	 */
	@Override
	public void configureResourceFactory(Registry registry) {
		registry.getExtensionToFactoryMap().put("basic", new BasicResourceFactoryImpl()); 
		registry.getContentTypeToFactoryMap().put("basic#1.0", new BasicResourceFactoryImpl()); 
	}
	
	/* 
	 * (non-Javadoc)
	 * @see org.geckoprojects.emf.core.ResourceFactoryConfigurator#unconfigureResourceFactory(org.eclipse.emf.ecore.resource.Resource.Factory.Registry)
	 * @generated
	 */
	@Override
	public void unconfigureResourceFactory(Registry registry) {
		registry.getExtensionToFactoryMap().remove("basic"); 
		registry.getContentTypeToFactoryMap().remove("basic#1.0"); 
	}
	
	/* 
	 * (non-Javadoc)
	 * @see org.geckoprojects.emf.core.EPackageRegistryConfigurator#configureEPackage(org.eclipse.emf.ecore.EPackage.Registry)
	 * @generated
	 */
	@Override
	public void configureEPackage(org.eclipse.emf.ecore.EPackage.Registry registry) {
		registry.put(BasicPackage.eNS_URI, BasicPackageImpl.init());
	}
	
	/* 
	 * (non-Javadoc)
	 * @see org.geckoprojects.emf.core.EPackageRegistryConfigurator#unconfigureEPackage(org.eclipse.emf.ecore.EPackage.Registry)
	 * @generated
	 */
	@Override
	public void unconfigureEPackage(org.eclipse.emf.ecore.EPackage.Registry registry) {
		if (packageRegistration != null) {
			packageRegistration.unregister();
		}
		registry.remove(BasicPackage.eNS_URI);
	}
	
	@Deactivate
	public void deactivate() {
		EPackage.Registry.INSTANCE.remove(BasicPackage.eNAME);
		if(packageRegistration != null){
			packageRegistration.unregister();
		}
	}
}
