/**
 */
package org.gecko.emf.osgi.example.model.basic;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Person Contact</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.emf.osgi.example.model.basic.PersonContact#getContactPerson <em>Contact Person</em>}</li>
 * </ul>
 *
 * @see org.gecko.emf.osgi.example.model.basic.BasicPackage#getPersonContact()
 * @model
 * @generated
 */
public interface PersonContact extends Contact {
	/**
	 * Returns the value of the '<em><b>Contact Person</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contact Person</em>' reference.
	 * @see #setContactPerson(Person)
	 * @see org.gecko.emf.osgi.example.model.basic.BasicPackage#getPersonContact_ContactPerson()
	 * @model
	 * @generated
	 */
	Person getContactPerson();

	/**
	 * Sets the value of the '{@link org.gecko.emf.osgi.example.model.basic.PersonContact#getContactPerson <em>Contact Person</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Contact Person</em>' reference.
	 * @see #getContactPerson()
	 * @generated
	 */
	void setContactPerson(Person value);

} // PersonContact
