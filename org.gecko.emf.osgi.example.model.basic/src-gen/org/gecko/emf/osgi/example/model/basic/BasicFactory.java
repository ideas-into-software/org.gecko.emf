/**
 */
package org.gecko.emf.osgi.example.model.basic;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.gecko.emf.osgi.example.model.basic.BasicPackage
 * @generated
 */
public interface BasicFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BasicFactory eINSTANCE = org.gecko.emf.osgi.example.model.basic.impl.BasicFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Person</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Person</em>'.
	 * @generated
	 */
	Person createPerson();

	/**
	 * Returns a new object of class '<em>Address</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Address</em>'.
	 * @generated
	 */
	Address createAddress();

	/**
	 * Returns a new object of class '<em>Contact</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Contact</em>'.
	 * @generated
	 */
	Contact createContact();

	/**
	 * Returns a new object of class '<em>Family</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Family</em>'.
	 * @generated
	 */
	Family createFamily();

	/**
	 * Returns a new object of class '<em>Business Contact</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Business Contact</em>'.
	 * @generated
	 */
	BusinessContact createBusinessContact();

	/**
	 * Returns a new object of class '<em>Tag</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tag</em>'.
	 * @generated
	 */
	Tag createTag();

	/**
	 * Returns a new object of class '<em>Business Person</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Business Person</em>'.
	 * @generated
	 */
	BusinessPerson createBusinessPerson();

	/**
	 * Returns a new object of class '<em>Employee Info</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Employee Info</em>'.
	 * @generated
	 */
	EmployeeInfo createEmployeeInfo();

	/**
	 * Returns a new object of class '<em>Widget</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Widget</em>'.
	 * @generated
	 */
	Widget createWidget();

	/**
	 * Returns a new object of class '<em>Textwidget</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Textwidget</em>'.
	 * @generated
	 */
	Textwidget createTextwidget();

	/**
	 * Returns a new object of class '<em>Content</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Content</em>'.
	 * @generated
	 */
	Content createContent();

	/**
	 * Returns a new object of class '<em>HL Widget</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>HL Widget</em>'.
	 * @generated
	 */
	HLWidget createHLWidget();

	/**
	 * Returns a new object of class '<em>Person Contact</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Person Contact</em>'.
	 * @generated
	 */
	PersonContact createPersonContact();

	/**
	 * Returns a new object of class '<em>Person Object</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Person Object</em>'.
	 * @generated
	 */
	PersonObject createPersonObject();

	/**
	 * Returns a new object of class '<em>Geometry</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Geometry</em>'.
	 * @generated
	 */
	Geometry createGeometry();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	BasicPackage getBasicPackage();

} //BasicFactory
