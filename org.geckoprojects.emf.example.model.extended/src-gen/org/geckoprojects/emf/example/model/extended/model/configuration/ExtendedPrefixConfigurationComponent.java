/*
 */
package org.geckoprojects.emf.example.model.extended.model.configuration;

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

import org.geckoprojects.emf.example.model.extended.model.ExtendedPrefixPackage;

import org.geckoprojects.emf.example.model.extended.model.impl.ExtendedPrefixPackageImpl;

import org.geckoprojects.emf.example.model.extended.model.util.ExtendedPrefixResourceFactoryImpl;

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
@Component(name="ExtendedPrefixConfigurator", service= {EPackageConfigurator.class, ResourceFactoryConfigurator.class})
@EMFModel(emf_model_name=ExtendedPrefixPackage.eNAME, emf_model_nsURI={ExtendedPrefixPackage.eNS_URI}, emf_model_version="1.0")
@RequireEMF
@ProvideEMFModel(name = ExtendedPrefixPackage.eNAME, nsURI = { ExtendedPrefixPackage.eNS_URI }, version = "1.0" )
@ProvideEMFResourceConfigurator( name = ExtendedPrefixPackage.eNAME,
	contentType = { "" }, 
	fileExtension = {
	"extendedprefix"
 	},  
	version = "1.0"
)
public class ExtendedPrefixConfigurationComponent implements EPackageConfigurator, ResourceFactoryConfigurator {
	private ServiceRegistration<?> packageRegistration = null;
	
	@Activate
	public void activate(BundleContext ctx) {
		ExtendedPrefixPackage p = ExtendedPrefixPackageImpl.init();
		if(p == null){
			p= ExtendedPrefixPackageImpl.eINSTANCE;
			EPackage.Registry.INSTANCE.put(ExtendedPrefixPackage.eNS_URI,p);
		}
		Dictionary<String, Object> properties = new Hashtable<String, Object>();
		properties.put("emf.model.name", ExtendedPrefixPackage.eNAME);
		properties.put("emf.model.nsURI", ExtendedPrefixPackage.eNS_URI);
		properties.put("fileExtension", "extendedprefix");
		String[] serviceClasses = new String[] {ExtendedPrefixPackage.class.getName(), EPackage.class.getName()};
		packageRegistration = ctx.registerService(serviceClasses, p, properties);
	}

	/* 
	 * (non-Javadoc)
	 * @see org.geckoprojects.emf.core.ResourceFactoryConfigurator#configureResourceFactory(org.eclipse.emf.ecore.resource.Resource.Factory.Registry)
	 * @generated
	 */
	@Override
	public void configureResourceFactory(Registry registry) {
		registry.getExtensionToFactoryMap().put("extendedprefix", new ExtendedPrefixResourceFactoryImpl()); 
		 
	}
	
	/* 
	 * (non-Javadoc)
	 * @see org.geckoprojects.emf.core.ResourceFactoryConfigurator#unconfigureResourceFactory(org.eclipse.emf.ecore.resource.Resource.Factory.Registry)
	 * @generated
	 */
	@Override
	public void unconfigureResourceFactory(Registry registry) {
		registry.getExtensionToFactoryMap().remove("extendedprefix"); 
		 
	}
	
	/* 
	 * (non-Javadoc)
	 * @see org.geckoprojects.emf.core.EPackageRegistryConfigurator#configureEPackage(org.eclipse.emf.ecore.EPackage.Registry)
	 * @generated
	 */
	@Override
	public void configureEPackage(org.eclipse.emf.ecore.EPackage.Registry registry) {
		registry.put(ExtendedPrefixPackage.eNS_URI, ExtendedPrefixPackageImpl.init());
	}
	
	/* 
	 * (non-Javadoc)
	 * @see org.geckoprojects.emf.core.EPackageRegistryConfigurator#unconfigureEPackage(org.eclipse.emf.ecore.EPackage.Registry)
	 * @generated
	 */
	@Override
	public void unconfigureEPackage(org.eclipse.emf.ecore.EPackage.Registry registry) {
		registry.remove(ExtendedPrefixPackage.eNS_URI);
	}
	
	@Deactivate
	public void deactivate() {
		EPackage.Registry.INSTANCE.remove(ExtendedPrefixPackage.eNS_URI);
		if(packageRegistration != null){
			packageRegistration.unregister();
		}
	}
}
