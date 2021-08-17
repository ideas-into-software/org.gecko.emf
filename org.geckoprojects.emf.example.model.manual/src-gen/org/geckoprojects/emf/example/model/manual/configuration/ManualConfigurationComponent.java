/*
 */
package org.geckoprojects.emf.example.model.manual.configuration;

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

import org.geckoprojects.emf.example.model.manual.ManualPackage;

import org.geckoprojects.emf.example.model.manual.impl.ManualPackageImpl;

import org.geckoprojects.emf.example.model.manual.util.ManualResourceFactoryImpl;

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
@Component(name="ManualConfigurator", service= {EPackageConfigurator.class, ResourceFactoryConfigurator.class})
@EMFModel(name=ManualPackage.eNAME, nsURI={ManualPackage.eNS_URI}, version="1.0.0")
@RequireEMF
@ProvideEMFModel(name = ManualPackage.eNAME, nsURI = { ManualPackage.eNS_URI }, version = "1.0.0")
@ProvideEMFResourceConfigurator( name = ManualPackage.eNAME,
	contentType = { "manual#1.0" }, 
	fileExtension = {
	"manual"
 	},  
	version = "1.0.0"
)
public class ManualConfigurationComponent implements EPackageConfigurator, ResourceFactoryConfigurator {
	private ServiceRegistration<?> packageRegistration = null;
	
	@Activate
	public void activate(BundleContext ctx) {
		ManualPackage p = ManualPackageImpl.init();
		if(p == null){
			p= ManualPackageImpl.eINSTANCE;
			EPackage.Registry.INSTANCE.put(ManualPackage.eNS_URI,p);
		}
		Dictionary<String, Object> properties = new Hashtable<String, Object>();
		properties.put("emf.model.name", ManualPackage.eNAME);
		properties.put("emf.model.nsURI", ManualPackage.eNS_URI);
		properties.put("fileExtension", "manual");
		properties.put("contentType", "manual#1.0");
		String[] serviceClasses = new String[] {ManualPackage.class.getName(), EPackage.class.getName()};
		packageRegistration = ctx.registerService(serviceClasses, p, properties);
	}

	/* 
	 * (non-Javadoc)
	 * @see org.geckoprojects.emf.core.ResourceFactoryConfigurator#configureResourceFactory(org.eclipse.emf.ecore.resource.Resource.Factory.Registry)
	 * @generated
	 */
	@Override
	public void configureResourceFactory(Registry registry) {
		registry.getExtensionToFactoryMap().put("manual", new ManualResourceFactoryImpl()); 
		registry.getContentTypeToFactoryMap().put("manual#1.0", new ManualResourceFactoryImpl()); 
	}
	
	/* 
	 * (non-Javadoc)
	 * @see org.geckoprojects.emf.core.ResourceFactoryConfigurator#unconfigureResourceFactory(org.eclipse.emf.ecore.resource.Resource.Factory.Registry)
	 * @generated
	 */
	@Override
	public void unconfigureResourceFactory(Registry registry) {
		registry.getExtensionToFactoryMap().remove("manual"); 
		registry.getContentTypeToFactoryMap().remove("manual#1.0"); 
	}
	
	/* 
	 * (non-Javadoc)
	 * @see org.geckoprojects.emf.core.EPackageRegistryConfigurator#configureEPackage(org.eclipse.emf.ecore.EPackage.Registry)
	 * @generated
	 */
	@Override
	public void configureEPackage(org.eclipse.emf.ecore.EPackage.Registry registry) {
		registry.put(ManualPackage.eNS_URI, ManualPackageImpl.init());
	}
	
	/* 
	 * (non-Javadoc)
	 * @see org.geckoprojects.emf.core.EPackageRegistryConfigurator#unconfigureEPackage(org.eclipse.emf.ecore.EPackage.Registry)
	 * @generated
	 */
	@Override
	public void unconfigureEPackage(org.eclipse.emf.ecore.EPackage.Registry registry) {
		registry.remove(ManualPackage.eNS_URI);
	}
	
	@Deactivate
	public void deactivate() {
		EPackage.Registry.INSTANCE.remove(ManualPackage.eNS_URI);
		if(packageRegistration != null){
			packageRegistration.unregister();
		}
	}
}
