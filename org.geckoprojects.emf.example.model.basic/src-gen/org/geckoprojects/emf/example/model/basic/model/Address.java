/**
 */
package org.geckoprojects.emf.example.model.basic.model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Address</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.model.Address#getStreet <em>Street</em>}</li>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.model.Address#getCity <em>City</em>}</li>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.model.Address#getZip <em>Zip</em>}</li>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.model.Address#getId <em>Id</em>}</li>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.model.Address#getNpe <em>Npe</em>}</li>
 * </ul>
 *
 * @see org.geckoprojects.emf.example.model.basic.model.BasicPackage#getAddress()
 * @model
 * @generated
 */
public interface Address extends EObject {
	/**
	 * Returns the value of the '<em><b>Street</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Street</em>' attribute.
	 * @see #setStreet(String)
	 * @see org.geckoprojects.emf.example.model.basic.model.BasicPackage#getAddress_Street()
	 * @model
	 * @generated
	 */
	String getStreet();

	/**
	 * Sets the value of the '{@link org.geckoprojects.emf.example.model.basic.model.Address#getStreet <em>Street</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Street</em>' attribute.
	 * @see #getStreet()
	 * @generated
	 */
	void setStreet(String value);

	/**
	 * Returns the value of the '<em><b>City</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>City</em>' attribute.
	 * @see #setCity(String)
	 * @see org.geckoprojects.emf.example.model.basic.model.BasicPackage#getAddress_City()
	 * @model
	 * @generated
	 */
	String getCity();

	/**
	 * Sets the value of the '{@link org.geckoprojects.emf.example.model.basic.model.Address#getCity <em>City</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>City</em>' attribute.
	 * @see #getCity()
	 * @generated
	 */
	void setCity(String value);

	/**
	 * Returns the value of the '<em><b>Zip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Zip</em>' attribute.
	 * @see #setZip(String)
	 * @see org.geckoprojects.emf.example.model.basic.model.BasicPackage#getAddress_Zip()
	 * @model
	 * @generated
	 */
	String getZip();

	/**
	 * Sets the value of the '{@link org.geckoprojects.emf.example.model.basic.model.Address#getZip <em>Zip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Zip</em>' attribute.
	 * @see #getZip()
	 * @generated
	 */
	void setZip(String value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.geckoprojects.emf.example.model.basic.model.BasicPackage#getAddress_Id()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.geckoprojects.emf.example.model.basic.model.Address#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Npe</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Npe</em>' attribute.
	 * @see #setNpe(NullPointerException)
	 * @see org.geckoprojects.emf.example.model.basic.model.BasicPackage#getAddress_Npe()
	 * @model dataType="org.geckoprojects.emf.example.model.basic.model.NPE"
	 * @generated
	 */
	NullPointerException getNpe();

	/**
	 * Sets the value of the '{@link org.geckoprojects.emf.example.model.basic.model.Address#getNpe <em>Npe</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Npe</em>' attribute.
	 * @see #getNpe()
	 * @generated
	 */
	void setNpe(NullPointerException value);

} // Address
