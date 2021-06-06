/**
 */
package org.geckoprojects.emf.example.model.extended.model;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.geckoprojects.emf.example.model.extended.model.ExtendedPrefixPackage
 * @generated
 */
public interface ExtendedPrefixFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ExtendedPrefixFactory eINSTANCE = org.geckoprojects.emf.example.model.extended.model.impl.ExtendedPrefixFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Extended Address</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Extended Address</em>'.
	 * @generated
	 */
	ExtendedAddress createExtendedAddress();

	/**
	 * Returns a new object of class '<em>Extended Person</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Extended Person</em>'.
	 * @generated
	 */
	ExtendedPerson createExtendedPerson();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ExtendedPrefixPackage getExtendedPrefixPackage();

} //ExtendedPrefixFactory
