/**
 */
package org.gecko.emf.osgi.example.model.basic;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tag</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.emf.osgi.example.model.basic.Tag#getName <em>Name</em>}</li>
 *   <li>{@link org.gecko.emf.osgi.example.model.basic.Tag#getValue <em>Value</em>}</li>
 *   <li>{@link org.gecko.emf.osgi.example.model.basic.Tag#getTag <em>Tag</em>}</li>
 *   <li>{@link org.gecko.emf.osgi.example.model.basic.Tag#getTags <em>Tags</em>}</li>
 *   <li>{@link org.gecko.emf.osgi.example.model.basic.Tag#getDescription <em>Description</em>}</li>
 * </ul>
 *
 * @see org.gecko.emf.osgi.example.model.basic.BasicPackage#getTag()
 * @model
 * @generated
 */
public interface Tag extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.gecko.emf.osgi.example.model.basic.BasicPackage#getTag_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.gecko.emf.osgi.example.model.basic.Tag#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see org.gecko.emf.osgi.example.model.basic.BasicPackage#getTag_Value()
	 * @model
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link org.gecko.emf.osgi.example.model.basic.Tag#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

	/**
	 * Returns the value of the '<em><b>Tag</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tag</em>' containment reference.
	 * @see #setTag(Tag)
	 * @see org.gecko.emf.osgi.example.model.basic.BasicPackage#getTag_Tag()
	 * @model containment="true"
	 * @generated
	 */
	Tag getTag();

	/**
	 * Sets the value of the '{@link org.gecko.emf.osgi.example.model.basic.Tag#getTag <em>Tag</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tag</em>' containment reference.
	 * @see #getTag()
	 * @generated
	 */
	void setTag(Tag value);

	/**
	 * Returns the value of the '<em><b>Tags</b></em>' containment reference list.
	 * The list contents are of type {@link org.gecko.emf.osgi.example.model.basic.Tag}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tags</em>' containment reference list.
	 * @see org.gecko.emf.osgi.example.model.basic.BasicPackage#getTag_Tags()
	 * @model containment="true"
	 * @generated
	 */
	EList<Tag> getTags();

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.gecko.emf.osgi.example.model.basic.BasicPackage#getTag_Description()
	 * @model extendedMetaData="name='desc'"
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.gecko.emf.osgi.example.model.basic.Tag#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

} // Tag
