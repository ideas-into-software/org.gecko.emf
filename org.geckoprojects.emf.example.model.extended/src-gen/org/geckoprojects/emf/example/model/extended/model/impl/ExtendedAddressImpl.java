/**
 */
package org.geckoprojects.emf.example.model.extended.model.impl;

import org.eclipse.emf.ecore.EClass;

import org.geckoprojects.emf.example.model.basic.model.impl.AddressImpl;

import org.geckoprojects.emf.example.model.extended.model.ExtendedAddress;
import org.geckoprojects.emf.example.model.extended.model.ExtendedPrefixPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Extended Address</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class ExtendedAddressImpl extends AddressImpl implements ExtendedAddress {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExtendedAddressImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExtendedPrefixPackage.Literals.EXTENDED_ADDRESS;
	}

} //ExtendedAddressImpl
