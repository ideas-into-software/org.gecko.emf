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
package org.gecko.emf.osgi.components;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryRegistryImpl;
import org.osgi.service.component.annotations.Component;

/**
 * Component for the {@link ResourceFactoryRegistryImpl}
 * @author Mark Hoffmann
 * @since 25.07.2017
 */
@Component(name="DefaultResourceFactoryRegistry", service=Resource.Factory.Registry.class)
public class DefaultResourceFactoryRegistryComponent extends ResourceFactoryRegistryImpl {

}