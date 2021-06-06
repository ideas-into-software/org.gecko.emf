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
package org.gecko.utilities.query.configurator;

import org.gecko.emf.osgi.EPackageConfigurator;
import org.gecko.emf.osgi.annotation.EMFModel;
import org.gecko.emf.osgi.annotation.provide.ProvideEMFModel;
import org.gecko.utilities.query.QueryPackage;
import org.osgi.service.component.annotations.Component;

/**
 * Configurator for the EMFCollection model
 * @author Mark Hoffmann
 * @since 26.07.2017
 */
@Component(name="QueryConfigurator", immediate=true, service=EPackageConfigurator.class)
@EMFModel(emf_model_fileExtension = "query", emf_model_name = QueryPackage.eNAME, emf_model_nsURI = QueryPackage.eNS_URI, emf_model_version = "1.0.0")
@ProvideEMFModel(name = QueryPackage.eNAME, nsURI = QueryPackage.eNS_URI, version = "1.0.0")
public class QueryConfigurator implements EPackageConfigurator {

	/* 
	 * (non-Javadoc)
	 * @see org.gecko.emf.osgi.EPackageConfigurator#configureEPackage(org.eclipse.emf.ecore.EPackage.Registry)
	 */
	@Override
	public void configureEPackage(org.eclipse.emf.ecore.EPackage.Registry registry) {
		QueryPackage.eINSTANCE.getClass();
		registry.put(QueryPackage.eNS_URI, QueryPackage.eINSTANCE);
	}

	/* 
	 * (non-Javadoc)
	 * @see org.gecko.emf.osgi.EPackageConfigurator#unconfigureEPackage(org.eclipse.emf.ecore.EPackage.Registry)
	 */
	@Override
	public void unconfigureEPackage(org.eclipse.emf.ecore.EPackage.Registry registry) {
		registry.remove(QueryPackage.eNS_URI);
	}

}
