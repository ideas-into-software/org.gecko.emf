/*
 * This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v2.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.gecko.emf.osgi.example.model.extended.configuration;

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

import org.gecko.emf.osgi.example.model.extended.ExtendedPackage;

import org.gecko.emf.osgi.example.model.extended.impl.ExtendedPackageImpl;

import org.gecko.emf.osgi.example.model.extended.util.ExtendedResourceFactoryImpl;

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
@Component(name="ExtendedConfigurator", service= {EPackageConfigurator.class, ResourceFactoryConfigurator.class})
@EMFModel(name=ExtendedPackage.eNAME, nsURI={ExtendedPackage.eNS_URI}, version="1.0.0")
@RequireEMF
@ProvideEMFModel(name = ExtendedPackage.eNAME, nsURI = { ExtendedPackage.eNS_URI }, version = "1.0.0")
@ProvideEMFResourceConfigurator( name = ExtendedPackage.eNAME,
	contentType = { "" }, 
	fileExtension = {
	"extended"
 	},  
	version = "1.0.0"
)
public class ExtendedConfigurationComponent implements EPackageConfigurator, ResourceFactoryConfigurator {
	private ServiceRegistration<?> packageRegistration = null;
	
	@Activate
	public void activate(BundleContext ctx) {
		ExtendedPackage p = ExtendedPackageImpl.init();
		if(p == null){
			p= ExtendedPackageImpl.eINSTANCE;
			EPackage.Registry.INSTANCE.put(ExtendedPackage.eNS_URI,p);
		}
		Dictionary<String, Object> properties = new Hashtable<String, Object>();
		properties.put(EMFNamespaces.EMF_MODEL_NAME, ExtendedPackage.eNAME);
		properties.put(EMFNamespaces.EMF_MODEL_NSURI, ExtendedPackage.eNS_URI);
		properties.put(EMFNamespaces.EMF_MODEL_FILE_EXT, "extended");
		String[] serviceClasses = new String[] {ExtendedPackage.class.getName(), EPackage.class.getName()};
		packageRegistration = ctx.registerService(serviceClasses, p, properties);
	}

	/* 
	 * (non-Javadoc)
	 * @see org.gecko.emf.osgi.core.ResourceFactoryConfigurator#configureResourceFactory(org.eclipse.emf.ecore.resource.Resource.Factory.Registry)
	 * @generated
	 */
	@Override
	public void configureResourceFactory(Registry registry) {
		registry.getExtensionToFactoryMap().put("extended", new ExtendedResourceFactoryImpl()); 
		 
	}
	
	/* 
	 * (non-Javadoc)
	 * @see org.gecko.emf.osgi.core.ResourceFactoryConfigurator#unconfigureResourceFactory(org.eclipse.emf.ecore.resource.Resource.Factory.Registry)
	 * @generated
	 */
	@Override
	public void unconfigureResourceFactory(Registry registry) {
		registry.getExtensionToFactoryMap().remove("extended"); 
		 
	}
	
	/* 
	 * (non-Javadoc)
	 * @see org.gecko.emf.osgi.core.EPackageRegistryConfigurator#configureEPackage(org.eclipse.emf.ecore.EPackage.Registry)
	 * @generated
	 */
	@Override
	public void configureEPackage(org.eclipse.emf.ecore.EPackage.Registry registry) {
		registry.put(ExtendedPackage.eNS_URI, ExtendedPackageImpl.init());
	}
	
	/* 
	 * (non-Javadoc)
	 * @see org.gecko.emf.osgi.core.EPackageRegistryConfigurator#unconfigureEPackage(org.eclipse.emf.ecore.EPackage.Registry)
	 * @generated
	 */
	@Override
	public void unconfigureEPackage(org.eclipse.emf.ecore.EPackage.Registry registry) {
		registry.remove(ExtendedPackage.eNS_URI);
	}
	
	@Deactivate
	public void deactivate() {
		EPackage.Registry.INSTANCE.remove(ExtendedPackage.eNS_URI);
		if(packageRegistration != null){
			packageRegistration.unregister();
		}
	}
}
