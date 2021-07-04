/**
 * Copyright (c) 2012 - 2018 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 * Copyright (c) 2012 - 2018 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 * 
 */
package org.geckoprojects.emf.utilities.configuration;

import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;
import org.geckoprojects.emf.core.EPackageConfigurator;
import org.geckoprojects.emf.core.ResourceFactoryConfigurator;
import org.geckoprojects.emf.core.annotation.EMFModel;
import org.geckoprojects.emf.core.annotation.provide.ProvideEMFModel;
import org.geckoprojects.emf.core.annotation.provide.ProvideEMFResourceConfigurator;
import org.geckoprojects.emf.core.annotation.require.RequireEMF;
import org.geckoprojects.emf.utilities.UtilPackage;
import org.geckoprojects.emf.utilities.util.UtilResourceFactoryImpl;
import org.osgi.service.component.annotations.Component;

/**
 * <!-- begin-user-doc -->
 * The <b>EPackageConfiguration</b> and <b>ResourceFactoryConfigurator</b> for the model.
 * The package will be registered into a OSGi base model registry.
 * <!-- end-user-doc -->
 * @see EPackageConfigurator
 * @see ResourceFactoryConfigurator
 * @generated
 */
@Component(name="UtilConfigurator", service= {EPackageConfigurator.class, ResourceFactoryConfigurator.class})
@EMFModel(emf_model_name=UtilPackage.eNAME, emf_model_nsURI={UtilPackage.eNS_URI}, emf_model_version="1.0")
@RequireEMF
@ProvideEMFModel(name = UtilPackage.eNAME, nsURI = { UtilPackage.eNS_URI }, version = "1.0" )
@ProvideEMFResourceConfigurator( name = UtilPackage.eNAME,
	contentType = { "" }, 
	fileExtension = {
	"util"
 	},  
	version = "1.0"
)
public class UtilConfigurationComponent implements EPackageConfigurator, ResourceFactoryConfigurator {


	@Override
	public void configureResourceFactory(Registry registry) {
		registry.getExtensionToFactoryMap().put("util", new UtilResourceFactoryImpl()); 
		 
	}
	

	@Override
	public void unconfigureResourceFactory(Registry registry) {
		registry.getExtensionToFactoryMap().remove("util"); 
		 
	}

	@Override
	public void configureEPackage(org.eclipse.emf.ecore.EPackage.Registry registry) {
		registry.put(UtilPackage.eNS_URI, UtilPackage.eINSTANCE);
	}
	

	@Override
	public void unconfigureEPackage(org.eclipse.emf.ecore.EPackage.Registry registry) {
		registry.remove(UtilPackage.eNS_URI);
	}
	
}
