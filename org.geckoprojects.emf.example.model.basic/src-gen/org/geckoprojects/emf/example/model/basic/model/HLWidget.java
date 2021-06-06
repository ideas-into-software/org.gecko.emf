/**
 */
package org.geckoprojects.emf.example.model.basic.model;

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
 *   <li>{@link org.geckoprojects.emf.example.model.basic.model.HLWidget#getChildren <em>Children</em>}</li>
 * </ul>
 *
 * @see org.geckoprojects.emf.example.model.basic.model.BasicPackage#getHLWidget()
 * @model
 * @generated
 */
public interface HLWidget extends Content {
	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link org.geckoprojects.emf.example.model.basic.model.Widget}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see org.geckoprojects.emf.example.model.basic.model.BasicPackage#getHLWidget_Children()
	 * @model containment="true"
	 * @generated
	 */
	EList<Widget> getChildren();

} // HLWidget
