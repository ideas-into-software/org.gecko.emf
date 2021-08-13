/**
 */
package org.geckoprojects.emf.example.model.extended.impl;

import org.eclipse.emf.ecore.EClass;

import org.geckoprojects.emf.example.model.basic.impl.AddressImpl;

import org.geckoprojects.emf.example.model.extended.ExtendedAddress;
import org.geckoprojects.emf.example.model.extended.ExtendedPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Address</b></em>'.
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
		return ExtendedPackage.Literals.EXTENDED_ADDRESS;
	}

} //ExtendedAddressImpl
