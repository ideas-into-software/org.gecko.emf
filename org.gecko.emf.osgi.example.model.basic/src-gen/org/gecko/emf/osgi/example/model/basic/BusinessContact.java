/**
 */
package org.gecko.emf.osgi.example.model.basic;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Business Contact</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.emf.osgi.example.model.basic.BusinessContact#getCompanyName <em>Company Name</em>}</li>
 * </ul>
 *
 * @see org.gecko.emf.osgi.example.model.basic.BasicPackage#getBusinessContact()
 * @model
 * @generated
 */
public interface BusinessContact extends Contact {
	/**
	 * Returns the value of the '<em><b>Company Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Company Name</em>' attribute.
	 * @see #setCompanyName(String)
	 * @see org.gecko.emf.osgi.example.model.basic.BasicPackage#getBusinessContact_CompanyName()
	 * @model
	 * @generated
	 */
	String getCompanyName();

	/**
	 * Sets the value of the '{@link org.gecko.emf.osgi.example.model.basic.BusinessContact#getCompanyName <em>Company Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Company Name</em>' attribute.
	 * @see #getCompanyName()
	 * @generated
	 */
	void setCompanyName(String value);

} // BusinessContact
