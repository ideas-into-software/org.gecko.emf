/**
 */
package org.geckoprojects.emf.example.model.basic;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Person</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.Person#getFirstName <em>First Name</em>}</li>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.Person#getLastName <em>Last Name</em>}</li>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.Person#getContact <em>Contact</em>}</li>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.Person#getAddress <em>Address</em>}</li>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.Person#getGender <em>Gender</em>}</li>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.Person#getTags <em>Tags</em>}</li>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.Person#getId <em>Id</em>}</li>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.Person#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.Person#getBigInt <em>Big Int</em>}</li>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.Person#getBigDec <em>Big Dec</em>}</li>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.Person#getImage <em>Image</em>}</li>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.Person#getRelatives <em>Relatives</em>}</li>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.Person#getTransientAddress <em>Transient Address</em>}</li>
 * </ul>
 *
 * @see org.geckoprojects.emf.example.model.basic.BasicPackage#getPerson()
 * @model
 * @generated
 */
public interface Person extends EObject {
	/**
	 * Returns the value of the '<em><b>First Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>First Name</em>' attribute.
	 * @see #setFirstName(String)
	 * @see org.geckoprojects.emf.example.model.basic.BasicPackage#getPerson_FirstName()
	 * @model
	 * @generated
	 */
	String getFirstName();

	/**
	 * Sets the value of the '{@link org.geckoprojects.emf.example.model.basic.Person#getFirstName <em>First Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>First Name</em>' attribute.
	 * @see #getFirstName()
	 * @generated
	 */
	void setFirstName(String value);

	/**
	 * Returns the value of the '<em><b>Last Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Last Name</em>' attribute.
	 * @see #setLastName(String)
	 * @see org.geckoprojects.emf.example.model.basic.BasicPackage#getPerson_LastName()
	 * @model
	 * @generated
	 */
	String getLastName();

	/**
	 * Sets the value of the '{@link org.geckoprojects.emf.example.model.basic.Person#getLastName <em>Last Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Last Name</em>' attribute.
	 * @see #getLastName()
	 * @generated
	 */
	void setLastName(String value);

	/**
	 * Returns the value of the '<em><b>Contact</b></em>' containment reference list.
	 * The list contents are of type {@link org.geckoprojects.emf.example.model.basic.Contact}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contact</em>' containment reference list.
	 * @see org.geckoprojects.emf.example.model.basic.BasicPackage#getPerson_Contact()
	 * @model containment="true"
	 * @generated
	 */
	EList<Contact> getContact();

	/**
	 * Returns the value of the '<em><b>Address</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Address</em>' reference.
	 * @see #setAddress(Address)
	 * @see org.geckoprojects.emf.example.model.basic.BasicPackage#getPerson_Address()
	 * @model
	 * @generated
	 */
	Address getAddress();

	/**
	 * Sets the value of the '{@link org.geckoprojects.emf.example.model.basic.Person#getAddress <em>Address</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Address</em>' reference.
	 * @see #getAddress()
	 * @generated
	 */
	void setAddress(Address value);

	/**
	 * Returns the value of the '<em><b>Gender</b></em>' attribute.
	 * The literals are from the enumeration {@link org.geckoprojects.emf.example.model.basic.GenderType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Gender</em>' attribute.
	 * @see org.geckoprojects.emf.example.model.basic.GenderType
	 * @see #setGender(GenderType)
	 * @see org.geckoprojects.emf.example.model.basic.BasicPackage#getPerson_Gender()
	 * @model
	 * @generated
	 */
	GenderType getGender();

	/**
	 * Sets the value of the '{@link org.geckoprojects.emf.example.model.basic.Person#getGender <em>Gender</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Gender</em>' attribute.
	 * @see org.geckoprojects.emf.example.model.basic.GenderType
	 * @see #getGender()
	 * @generated
	 */
	void setGender(GenderType value);

	/**
	 * Returns the value of the '<em><b>Tags</b></em>' containment reference list.
	 * The list contents are of type {@link org.geckoprojects.emf.example.model.basic.Tag}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tags</em>' containment reference list.
	 * @see org.geckoprojects.emf.example.model.basic.BasicPackage#getPerson_Tags()
	 * @model containment="true"
	 * @generated
	 */
	EList<Tag> getTags();

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.geckoprojects.emf.example.model.basic.BasicPackage#getPerson_Id()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.geckoprojects.emf.example.model.basic.Person#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Properties</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Properties</em>' map.
	 * @see org.geckoprojects.emf.example.model.basic.BasicPackage#getPerson_Properties()
	 * @model mapType="org.geckoprojects.emf.example.model.basic.StringStringMap&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;"
	 * @generated
	 */
	EMap<String, String> getProperties();

	/**
	 * Returns the value of the '<em><b>Big Int</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Big Int</em>' attribute.
	 * @see #setBigInt(BigInteger)
	 * @see org.geckoprojects.emf.example.model.basic.BasicPackage#getPerson_BigInt()
	 * @model
	 * @generated
	 */
	BigInteger getBigInt();

	/**
	 * Sets the value of the '{@link org.geckoprojects.emf.example.model.basic.Person#getBigInt <em>Big Int</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Big Int</em>' attribute.
	 * @see #getBigInt()
	 * @generated
	 */
	void setBigInt(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Big Dec</b></em>' attribute list.
	 * The list contents are of type {@link java.math.BigDecimal}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Big Dec</em>' attribute list.
	 * @see org.geckoprojects.emf.example.model.basic.BasicPackage#getPerson_BigDec()
	 * @model
	 * @generated
	 */
	EList<BigDecimal> getBigDec();

	/**
	 * Returns the value of the '<em><b>Image</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Image</em>' attribute.
	 * @see #setImage(byte[])
	 * @see org.geckoprojects.emf.example.model.basic.BasicPackage#getPerson_Image()
	 * @model
	 * @generated
	 */
	byte[] getImage();

	/**
	 * Sets the value of the '{@link org.geckoprojects.emf.example.model.basic.Person#getImage <em>Image</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Image</em>' attribute.
	 * @see #getImage()
	 * @generated
	 */
	void setImage(byte[] value);

	/**
	 * Returns the value of the '<em><b>Relatives</b></em>' reference list.
	 * The list contents are of type {@link org.geckoprojects.emf.example.model.basic.Person}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relatives</em>' reference list.
	 * @see org.geckoprojects.emf.example.model.basic.BasicPackage#getPerson_Relatives()
	 * @model
	 * @generated
	 */
	EList<Person> getRelatives();

	/**
	 * Returns the value of the '<em><b>Transient Address</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transient Address</em>' reference.
	 * @see #setTransientAddress(Address)
	 * @see org.geckoprojects.emf.example.model.basic.BasicPackage#getPerson_TransientAddress()
	 * @model transient="true"
	 * @generated
	 */
	Address getTransientAddress();

	/**
	 * Sets the value of the '{@link org.geckoprojects.emf.example.model.basic.Person#getTransientAddress <em>Transient Address</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transient Address</em>' reference.
	 * @see #getTransientAddress()
	 * @generated
	 */
	void setTransientAddress(Address value);

} // Person
