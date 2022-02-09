/**
 */
package org.gecko.emf.osgi.example.model.basic;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>HL Widget</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.emf.osgi.example.model.basic.HLWidget#getChildren <em>Children</em>}</li>
 * </ul>
 *
 * @see org.gecko.emf.osgi.example.model.basic.BasicPackage#getHLWidget()
 * @model
 * @generated
 */
public interface HLWidget extends Content {
	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link org.gecko.emf.osgi.example.model.basic.Widget}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see org.gecko.emf.osgi.example.model.basic.BasicPackage#getHLWidget_Children()
	 * @model containment="true"
	 * @generated
	 */
	EList<Widget> getChildren();

} // HLWidget
