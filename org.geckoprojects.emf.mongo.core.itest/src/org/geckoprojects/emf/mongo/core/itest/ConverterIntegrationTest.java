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
package org.geckoprojects.emf.mongo.core.itest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.io.IOException;
import java.util.Hashtable;

import org.geckoprojects.emf.example.model.basic.BasicPackage;
import org.geckoprojects.emf.mongo.ConverterService;
import org.geckoprojects.emf.mongo.ValueConverter;
import org.geckoprojects.emf.mongo.converter.DefaultConverterService;
import org.geckoprojects.emf.mongo.core.itest.converter.NPEConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceRegistration;
import org.osgi.test.common.annotation.InjectBundleContext;
import org.osgi.test.common.annotation.InjectService;
import org.osgi.test.common.service.ServiceAware;
import org.osgi.test.junit5.context.BundleContextExtension;
import org.osgi.test.junit5.service.ServiceExtension;

/**
 * Integration tests for the complete EMF mongo setup
 * 
 * @author Mark Hoffmann
 * @since 26.07.2017
 */

@SuppressWarnings("restriction") // Fragment Host
@ExtendWith(BundleContextExtension.class)
@ExtendWith(ServiceExtension.class)
public class ConverterIntegrationTest {

	@InjectBundleContext
	BundleContext bc;

	@Test
	public void testDefaultConverterService(@InjectService ConverterService converterService) {
		assertThat(converterService).isInstanceOf(DefaultConverterService.class);
	}

	@Test
	public void testDefaultConverterServiceRegistration_Fail(@InjectService ConverterService converterService)
			throws BundleException, InvalidSyntaxException, IOException, InterruptedException {

		assertThat(converterService).isInstanceOf(DefaultConverterService.class);

		assertThatExceptionOfType(IllegalStateException.class)
				.isThrownBy(() -> converterService.getConverter(BasicPackage.Literals.NPE));
	}

	@Test
	public void testDefaultConverterServiceRegistrationNew(@InjectService ConverterService converterService,
			@InjectService(cardinality = 0) ServiceAware<ValueConverter> savc)
			throws BundleException, InvalidSyntaxException, IOException, InterruptedException {
		
		assertThat(converterService).isInstanceOf(DefaultConverterService.class);

		assertThatExceptionOfType(IllegalStateException.class)
				.isThrownBy(() -> converterService.getConverter(BasicPackage.Literals.NPE));

		assertThat(savc.getTrackingCount()).isEqualTo(0);
		
		ValueConverter converter = new NPEConverter();
		ServiceRegistration<ValueConverter> reg = bc.registerService(ValueConverter.class, converter,
				new Hashtable<>());

		assertThat(savc.getTrackingCount()).isEqualTo(1);

	
		assertThat(converter).isEqualTo(savc.getService());


		assertThat(converter).isEqualTo(converterService.getConverter(BasicPackage.Literals.NPE));

		reg.unregister();

		assertThatExceptionOfType(IllegalStateException.class)
				.isThrownBy(() -> converterService.getConverter(BasicPackage.Literals.NPE));
	}

}
