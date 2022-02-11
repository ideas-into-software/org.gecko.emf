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
package org.gecko.emf.osgi.doc.mermaid.itest;

import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.gecko.emf.osgi.doc.mermaid.MermaidService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.framework.BundleContext;
import org.osgi.test.common.annotation.InjectBundleContext;
import org.osgi.test.common.annotation.InjectService;
import org.osgi.test.junit5.context.BundleContextExtension;
import org.osgi.test.junit5.service.ServiceExtension;

@ExtendWith(BundleContextExtension.class)
@ExtendWith(ServiceExtension.class)
public class MainTest {

	@InjectBundleContext
	BundleContext bc;

	@Test
	public void main(@InjectService MermaidService mermaidService) throws Exception {

		String ecorePath = "./model/bsm.ecore";
		Writer consoleWriter = new OutputStreamWriter(System.out);
		Writer fileWriter = new FileWriter(new File("bsm.md"));

		mermaidService.generate(ecorePath, fileWriter);
		
		 ecorePath = "./asset/asset.ecore";
		 consoleWriter = new OutputStreamWriter(System.out);
		 fileWriter = new FileWriter(new File("asset.md"));

		mermaidService.generate(ecorePath, fileWriter);

	}
}