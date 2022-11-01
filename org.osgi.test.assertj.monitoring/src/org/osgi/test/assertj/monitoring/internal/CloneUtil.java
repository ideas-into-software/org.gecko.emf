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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.stream.Stream;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.FrameworkEvent;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceReference;

class CloneUtil {
	static ServiceEvent clone(ServiceEvent serviceEvent) {
		return new ServiceEvent(serviceEvent.getType(), clone(serviceEvent.getServiceReference()));
	}

	static BundleEvent clone(BundleEvent bundleEvent) {
		return new BundleEvent(bundleEvent.getType(), clone(bundleEvent.getBundle()), clone(bundleEvent.getOrigin()));
	}

	static FrameworkEvent clone(FrameworkEvent frameworkEvent) {
		return new FrameworkEvent(frameworkEvent.getType(), clone(frameworkEvent.getBundle()),
				frameworkEvent.getThrowable());
	}

	static ServiceReference<?> clone(ServiceReference<?> serviceReference) {
		Map<String, Object> props = new HashMap<>();
		if (serviceReference.getPropertyKeys() != null) {
			for (String key : serviceReference.getPropertyKeys()) {
				props.put(key, serviceReference.getProperty(key));
			}
		}
		Bundle bundle = clone(serviceReference.getBundle());
		Bundle[] usingBundles = serviceReference.getUsingBundles() == null ? null
				: Stream.of(serviceReference.getUsingBundles()).map(b -> clone(b)).toArray(Bundle[]::new);
		ServiceReference<?> serviceReferenceProxyInstance = (ServiceReference<?>) Proxy.newProxyInstance(
				ServiceReference.class.getClassLoader(), new Class[] { ServiceReference.class },
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						if (method.getName().equals("getProperty")) {
							return props.get(args[0]);
						}
						if (method.getName().equals("getPropertyKeys")) {
							return props.keySet().stream().toArray(String[]::new);
						}
						if (method.getName().equals("getProperties")) {
							return new Hashtable<>(props);
						}
						if (method.getName().equals("getBundle")) {
							return bundle;
						}
						if (method.getName().equals("getUsingBundles")) {
							return usingBundles;
						}
						return method.invoke(serviceReference, args);
					}
				});
		return serviceReferenceProxyInstance;
	}

	static Bundle clone(Bundle bundle) {
		if (bundle == null) {
			return null;
		}
		int state = bundle.getState();
		long lastModified = bundle.getLastModified();
		Bundle bundleProxyInstance = (Bundle) Proxy.newProxyInstance(Bundle.class.getClassLoader(),
				new Class[] { Bundle.class }, new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						if (method.getName().equals("getState")) {
							return state;
						}
						if (method.getName().equals("getLastModified")) {
							return lastModified;
						}
						return method.invoke(bundle, args);
					}
				});
		return bundleProxyInstance;
	}
}
