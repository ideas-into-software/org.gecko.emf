/**
 * Copyright (c) 2012 - 2022 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v2.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *      Data In Motion - initial API and implementation
 */
package org.gecko.emf.osgi.components.config;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryRegistryImpl;
import org.gecko.emf.osgi.EMFNamespaces;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

/**
 * Component for the {@link ResourceFactoryRegistryImpl}
 * @author Mark Hoffmann
 * @since 25.07.2017
 */
@Component(configurationPid=EMFNamespaces.RESOURCE_FACTORY_CONFIG_NAME, service=Resource.Factory.Registry.class, configurationPolicy=ConfigurationPolicy.REQUIRE)
public class ConfigurationResourceFactoryRegistryComponent extends ResourceFactoryRegistryImpl {

}
