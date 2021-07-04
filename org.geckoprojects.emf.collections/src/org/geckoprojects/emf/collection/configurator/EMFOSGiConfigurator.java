/**
 * Copyright (c) 2012 - 2017 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package org.geckoprojects.emf.collection.configurator;

import org.geckoprojects.emf.collection.CollectionPackage;
import org.geckoprojects.emf.core.EPackageConfigurator;
import org.geckoprojects.emf.core.annotation.EMFModel;
import org.geckoprojects.emf.core.annotation.provide.ProvideEMFModel;
import org.osgi.service.component.annotations.Component;

/**
 * Configurator for the EMFCollection model
 * @author Mark Hoffmann
 * @since 26.07.2017
 */
@Component(name="EMFCollectionConfigurator", immediate=true, service=EPackageConfigurator.class)
@EMFModel(emf_model_name= CollectionPackage.eNAME, emf_model_nsURI = CollectionPackage.eNS_URI, emf_model_version = "2.0")
@ProvideEMFModel(name = CollectionPackage.eNAME, nsURI = CollectionPackage.eNS_URI, version = "2.0")
public class EMFOSGiConfigurator implements EPackageConfigurator {


	@Override
	public void configureEPackage(org.eclipse.emf.ecore.EPackage.Registry registry) {
		CollectionPackage.eINSTANCE.getClass();
		registry.put(CollectionPackage.eNS_URI, CollectionPackage.eINSTANCE);
	}

	@Override
	public void unconfigureEPackage(org.eclipse.emf.ecore.EPackage.Registry registry) {
		registry.remove(CollectionPackage.eNS_URI);
	}

}
