/**
 */
package org.geckoprojects.emf.example.model.basic.model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Contact</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.model.Contact#getType <em>Type</em>}</li>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.model.Contact#getValue <em>Value</em>}</li>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.model.Contact#getContext <em>Context</em>}</li>
 * </ul>
 *
 * @see org.geckoprojects.emf.example.model.basic.model.BasicPackage#getContact()
 * @model
 * @generated
 */
public interface Contact extends EObject {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.geckoprojects.emf.example.model.basic.model.ContactType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.geckoprojects.emf.example.model.basic.model.ContactType
	 * @see #setType(ContactType)
	 * @see org.geckoprojects.emf.example.model.basic.model.BasicPackage#getContact_Type()
	 * @model
	 * @generated
	 */
	ContactType getType();

	/**
	 * Sets the value of the '{@link org.geckoprojects.emf.example.model.basic.model.Contact#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see org.geckoprojects.emf.example.model.basic.model.ContactType
	 * @see #getType()
	 * @generated
	 */
	void setType(ContactType value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see org.geckoprojects.emf.example.model.basic.model.BasicPackage#getContact_Value()
	 * @model
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link org.geckoprojects.emf.example.model.basic.model.Contact#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

	/**
	 * Returns the value of the '<em><b>Context</b></em>' attribute.
	 * The literals are from the enumeration {@link org.geckoprojects.emf.example.model.basic.model.ContactContextType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Context</em>' attribute.
	 * @see org.geckoprojects.emf.example.model.basic.model.ContactContextType
	 * @see #setContext(ContactContextType)
	 * @see org.geckoprojects.emf.example.model.basic.model.BasicPackage#getContact_Context()
	 * @model
	 * @generated
	 */
	ContactContextType getContext();

	/**
	 * Sets the value of the '{@link org.geckoprojects.emf.example.model.basic.model.Contact#getContext <em>Context</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Context</em>' attribute.
	 * @see org.geckoprojects.emf.example.model.basic.model.ContactContextType
	 * @see #getContext()
	 * @generated
	 */
	void setContext(ContactContextType value);

} // Contact
