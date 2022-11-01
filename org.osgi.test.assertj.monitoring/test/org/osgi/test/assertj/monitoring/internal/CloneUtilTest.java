/**
 * Copyright (c) 2012 - 2022 Data In Motion and others.
 * All rights reserved. 
 *  
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 *  
 * Contributors:
 *       Data In Motion - initial API and implementation
 */
package org.osgi.test.assertj.monitoring.internal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceReference;

@Disabled
public class CloneUtilTest {

	@Test
	public void testCloneBundle() throws Exception {
		Bundle bundle = mock(Bundle.class);
		when(bundle.getState()).thenReturn(Bundle.INSTALLED);
		when(bundle.getLastModified()).thenReturn(1l);

		when(bundle.getSymbolicName()).thenReturn("foo");
		Bundle copyBundle = CloneUtil.clone(bundle);

		// change state
		when(bundle.getState()).thenReturn(Bundle.UNINSTALLED);
		when(bundle.getLastModified()).thenReturn(2l);

		assertThat(bundle.getState()).isEqualTo(Bundle.UNINSTALLED);
		assertThat(copyBundle.getState()).isEqualTo(Bundle.INSTALLED);

		assertThat(bundle.getLastModified()).isEqualTo(2l);
		assertThat(copyBundle.getLastModified()).isEqualTo(1l);

		assertThat(copyBundle.getSymbolicName()).isEqualTo("foo");
	}

	@Test
	public void testCloneSR() throws Exception {
		ServiceReference<?> sr = mock(ServiceReference.class);

		when(sr.getPropertyKeys()).thenReturn(new String[] { "a" });
		when(sr.getProperty("a")).thenReturn("1");

		when(sr.toString()).thenReturn("foo");

		ServiceReference<?> copySr = CloneUtil.clone(sr);

		// change state
		when(sr.getProperty("a")).thenReturn("3");

		assertThat(sr.getProperty("a")).isEqualTo("3");
		assertThat(copySr.getProperty("a")).isEqualTo("1");

		assertThat(copySr.toString()).isEqualTo("foo");
	}

}