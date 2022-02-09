/**
 */
package org.gecko.emf.osgi.example.model.extended.impl;

import org.eclipse.emf.ecore.EClass;

import org.gecko.emf.osgi.example.model.basic.impl.AddressImpl;

import org.gecko.emf.osgi.example.model.extended.ExtendedAddress;
import org.gecko.emf.osgi.example.model.extended.ExtendedPackage;

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
