/**
 */
package org.geckoprojects.emf.example.model.basic.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Business Person</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.model.BusinessPerson#getCompanyIdCardNumber <em>Company Id Card Number</em>}</li>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.model.BusinessPerson#getEmployeeInfo <em>Employee Info</em>}</li>
 * </ul>
 *
 * @see org.geckoprojects.emf.example.model.basic.model.BasicPackage#getBusinessPerson()
 * @model
 * @generated
 */
public interface BusinessPerson extends Person {
	/**
	 * Returns the value of the '<em><b>Company Id Card Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Company Id Card Number</em>' attribute.
	 * @see #setCompanyIdCardNumber(String)
	 * @see org.geckoprojects.emf.example.model.basic.model.BasicPackage#getBusinessPerson_CompanyIdCardNumber()
	 * @model extendedMetaData="name='compId'"
	 * @generated
	 */
	String getCompanyIdCardNumber();

	/**
	 * Sets the value of the '{@link org.geckoprojects.emf.example.model.basic.model.BusinessPerson#getCompanyIdCardNumber <em>Company Id Card Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Company Id Card Number</em>' attribute.
	 * @see #getCompanyIdCardNumber()
	 * @generated
	 */
	void setCompanyIdCardNumber(String value);

	/**
	 * Returns the value of the '<em><b>Employee Info</b></em>' containment reference list.
	 * The list contents are of type {@link org.geckoprojects.emf.example.model.basic.model.EmployeeInfo}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Employee Info</em>' containment reference list.
	 * @see org.geckoprojects.emf.example.model.basic.model.BasicPackage#getBusinessPerson_EmployeeInfo()
	 * @model containment="true"
	 *        extendedMetaData="name='eInfo'"
	 * @generated
	 */
	EList<EmployeeInfo> getEmployeeInfo();

} // BusinessPerson
