/**
 */
package org.geckoprojects.emf.example.model.basic.model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Employee Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.model.EmployeeInfo#getPosition <em>Position</em>}</li>
 * </ul>
 *
 * @see org.geckoprojects.emf.example.model.basic.model.BasicPackage#getEmployeeInfo()
 * @model
 * @generated
 */
public interface EmployeeInfo extends EObject {
	/**
	 * Returns the value of the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Position</em>' attribute.
	 * @see #setPosition(String)
	 * @see org.geckoprojects.emf.example.model.basic.model.BasicPackage#getEmployeeInfo_Position()
	 * @model
	 * @generated
	 */
	String getPosition();

	/**
	 * Sets the value of the '{@link org.geckoprojects.emf.example.model.basic.model.EmployeeInfo#getPosition <em>Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Position</em>' attribute.
	 * @see #getPosition()
	 * @generated
	 */
	void setPosition(String value);

} // EmployeeInfo
