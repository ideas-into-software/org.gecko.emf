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
 */
package org.geckoprojects.emf.model.info.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.geckoprojects.emf.core.api.EPackageConfigurator;
import org.geckoprojects.emf.model.info.EMFModelInfo;
import org.osgi.annotation.bundle.Capability;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * An implementation for the {@link EMFModelInfo} service
 * 
 * @author Juergen Albert
 * @since 8 Nov 2018
 */
@Capability(namespace = EMFModelInfo.NAMESPACE, name = EMFModelInfo.NAME)
@Component(service = EMFModelInfo.class)
public class EMFModelInfoImpl extends HashMap<String, Object> implements EMFModelInfo, EPackage.Registry {

	/** serialVersionUID */
	private static final long serialVersionUID = 7749336016374647599L;

//	private Map<String, EPackage> packages = new ConcurrentHashMap<>();
	private Map<Class<?>, EClassifier> classes = new ConcurrentHashMap<>();
	private Map<EClass, List<EClass>> upperHirachy = new ConcurrentHashMap<>();

	private Map<EClass, List<EClass>> needsRevisiting = new ConcurrentHashMap<>();

	List<EPackageConfigurator> list = new ArrayList<>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.geckoprojects.emf.model.info.EMFModelInfo#getEClassifierForClass(java.
	 * lang.Class)
	 */
	@Override
	public Optional<EClassifier> getEClassifierForClass(Class<?> clazz) {
		return classes.entrySet().stream().filter(e -> e.getKey().equals(clazz)).map(e -> e.getValue()).findFirst();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.geckoprojects.emf.model.info.EMFModelInfo#getEClassifierForClass(java.
	 * lang.String)
	 */
	@Override
	public Optional<EClassifier> getEClassifierForClass(String fullQualifiedClassName) {
		return classes.entrySet().stream().filter(e -> e.getKey().getName().equals(fullQualifiedClassName))
				.map(e -> e.getValue()).findFirst();
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC, policyOption = ReferencePolicyOption.GREEDY)
	public void bindEPackageConfigurator(EPackageConfigurator configurator) {
		System.out.println("+" + configurator);
		list.add(configurator);
		refresh();
	}

	private synchronized void refresh() {
//		packages = new ConcurrentHashMap<>();
		classes = new ConcurrentHashMap<>();
		upperHirachy = new ConcurrentHashMap<>();

		needsRevisiting = new ConcurrentHashMap<>();
		System.out.println("-------");

		list.forEach(c -> {
			System.out.println("=" + c);
			c.configureEPackage(this);
		});

	}

	public void unbindEPackageConfigurator(EPackageConfigurator configurator) {
		list.remove(configurator);
		configurator.unconfigureEPackage(this);
		System.out.println("-" + configurator);
		refresh();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.HashMap#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public Object put(String uri, Object value) {
		if (value instanceof EPackage) {
			EPackage ePackage = (EPackage) value;
//			packages.put(uri, ePackage);
			addEClassesOfEPackage(ePackage);
		}
		return null;
	}

	private synchronized void addEClassesOfEPackage(EPackage ePackage) {
		ePackage.getEClassifiers().forEach(ec -> {
			analyseHirachy(ec);
			Class<?> instanceClass = ec.getInstanceClass();
			if (instanceClass != DynamicEObjectImpl.class) {
				classes.put(instanceClass, ec);
			}
		});
	}

	/**
	 * @param ec EClass to analyze the Hierarchy for
	 */
	private void analyseHirachy(EClassifier ec) {
		if (!(ec instanceof EClass) || ec.getEPackage().equals(EcorePackage.eINSTANCE)) {
			return;
		}
		EClass eClass = (EClass) ec;
		List<EClass> thisHirachy = needsRevisiting.remove(eClass);
		if (thisHirachy == null) {
			thisHirachy = Collections.synchronizedList(new LinkedList<EClass>());
		}
		upperHirachy.put(eClass, thisHirachy);
		eClass.getEAllSuperTypes().forEach(superEClass -> {
			if (superEClass.equals(EcorePackage.Literals.ECLASS)) {
				return;
			}
			if (upperHirachy.containsKey(superEClass)) {
				List<EClass> hierarchy = upperHirachy.get(superEClass);
				if (!hierarchy.contains(superEClass)) {
					hierarchy.add(eClass);
				}
			} else {
				List<EClass> otherHierachy = needsRevisiting.getOrDefault(superEClass,
						Collections.synchronizedList(new LinkedList<EClass>()));
				otherHierachy.add(eClass);
				needsRevisiting.put(superEClass, otherHierachy);
			}
		});

	}

//	protected void putEntry(Map.Entry<? extends String, ? extends Object> entry) {
//		put(entry.getKey(), entry.getValue());
//	}

//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see java.util.HashMap#putAll(java.util.Map)
//	 */
//	@Override
//	public void putAll(Map<? extends String, ? extends Object> map) {
//		map.entrySet().stream().forEach(this::putEntry);
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry#getEFactory(java.lang.String)
	 */
	@Override
	public EFactory getEFactory(String uri) {
		throw new UnsupportedOperationException("This method must not be called");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry#getEPackage(java.lang.String)
	 */
	@Override
	public EPackage getEPackage(String nsUri) {
//		return packages.get(nsUri);
		throw new UnsupportedOperationException("This method must not be called");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.geckoprojects.emf.model.info.EMFModelInfo#getUpperTypeHierarchyForEClass(
	 * org.eclipse.emf.ecore.EClass)
	 */
	@Override
	public List<EClass> getUpperTypeHierarchyForEClass(EClass eClass) {
		if (!upperHirachy.containsKey(eClass)) {
			return Collections.emptyList();
		}

		return upperHirachy.get(eClass);
	}
}