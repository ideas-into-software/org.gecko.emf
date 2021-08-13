/**
 */
package org.geckoprojects.emf.example.model.basic;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Family</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.Family#getFather <em>Father</em>}</li>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.Family#getMother <em>Mother</em>}</li>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.Family#getChildren <em>Children</em>}</li>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.Family#getId <em>Id</em>}</li>
 * </ul>
 *
 * @see org.geckoprojects.emf.example.model.basic.BasicPackage#getFamily()
 * @model
 * @generated
 */
public interface Family extends EObject {
	/**
	 * Returns the value of the '<em><b>Father</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Father</em>' reference.
	 * @see #setFather(Person)
	 * @see org.geckoprojects.emf.example.model.basic.BasicPackage#getFamily_Father()
	 * @model
	 * @generated
	 */
	Person getFather();

	/**
	 * Sets the value of the '{@link org.geckoprojects.emf.example.model.basic.Family#getFather <em>Father</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Father</em>' reference.
	 * @see #getFather()
	 * @generated
	 */
	void setFather(Person value);

	/**
	 * Returns the value of the '<em><b>Mother</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mother</em>' reference.
	 * @see #setMother(Person)
	 * @see org.geckoprojects.emf.example.model.basic.BasicPackage#getFamily_Mother()
	 * @model
	 * @generated
	 */
	Person getMother();

	/**
	 * Sets the value of the '{@link org.geckoprojects.emf.example.model.basic.Family#getMother <em>Mother</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mother</em>' reference.
	 * @see #getMother()
	 * @generated
	 */
	void setMother(Person value);

	/**
	 * Returns the value of the '<em><b>Children</b></em>' reference list.
	 * The list contents are of type {@link org.geckoprojects.emf.example.model.basic.Person}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' reference list.
	 * @see org.geckoprojects.emf.example.model.basic.BasicPackage#getFamily_Children()
	 * @model
	 * @generated
	 */
	EList<Person> getChildren();

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.geckoprojects.emf.example.model.basic.BasicPackage#getFamily_Id()
	 * @model id="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.geckoprojects.emf.example.model.basic.Family#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // Family
