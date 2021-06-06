/**
 */
package org.geckoprojects.emf.example.model.extended.model.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.geckoprojects.emf.example.model.extended.model.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ExtendedPrefixFactoryImpl extends EFactoryImpl implements ExtendedPrefixFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ExtendedPrefixFactory init() {
		try {
			ExtendedPrefixFactory theExtendedPrefixFactory = (ExtendedPrefixFactory)EPackage.Registry.INSTANCE.getEFactory(ExtendedPrefixPackage.eNS_URI);
			if (theExtendedPrefixFactory != null) {
				return theExtendedPrefixFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ExtendedPrefixFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExtendedPrefixFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ExtendedPrefixPackage.EXTENDED_ADDRESS: return createExtendedAddress();
			case ExtendedPrefixPackage.EXTENDED_PERSON: return createExtendedPerson();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExtendedAddress createExtendedAddress() {
		ExtendedAddressImpl extendedAddress = new ExtendedAddressImpl();
		return extendedAddress;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExtendedPerson createExtendedPerson() {
		ExtendedPersonImpl extendedPerson = new ExtendedPersonImpl();
		return extendedPerson;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExtendedPrefixPackage getExtendedPrefixPackage() {
		return (ExtendedPrefixPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ExtendedPrefixPackage getPackage() {
		return ExtendedPrefixPackage.eINSTANCE;
	}

} //ExtendedPrefixFactoryImpl
