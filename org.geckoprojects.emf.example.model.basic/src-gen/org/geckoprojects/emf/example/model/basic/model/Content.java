/**
 */
package org.geckoprojects.emf.example.model.basic.model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.model.Content#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see org.geckoprojects.emf.example.model.basic.model.BasicPackage#getContent()
 * @model
 * @generated
 */
public interface Content extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.geckoprojects.emf.example.model.basic.model.BasicPackage#getContent_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.geckoprojects.emf.example.model.basic.model.Content#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // Content
