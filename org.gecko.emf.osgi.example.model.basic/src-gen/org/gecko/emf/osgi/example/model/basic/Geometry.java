/**
 */
package org.gecko.emf.osgi.example.model.basic;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Geometry</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.emf.osgi.example.model.basic.Geometry#getCoordinates <em>Coordinates</em>}</li>
 *   <li>{@link org.gecko.emf.osgi.example.model.basic.Geometry#getId <em>Id</em>}</li>
 *   <li>{@link org.gecko.emf.osgi.example.model.basic.Geometry#getMultiDimensionalCoordinates <em>Multi Dimensional Coordinates</em>}</li>
 * </ul>
 *
 * @see org.gecko.emf.osgi.example.model.basic.BasicPackage#getGeometry()
 * @model
 * @generated
 */
public interface Geometry extends EObject {
	/**
	 * Returns the value of the '<em><b>Coordinates</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}<code>[]</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Coordinates</em>' attribute list.
	 * @see org.gecko.emf.osgi.example.model.basic.BasicPackage#getGeometry_Coordinates()
	 * @model dataType="org.gecko.emf.osgi.example.model.basic.Coordinates"
	 * @generated
	 */
	EList<Double[]> getCoordinates();

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.gecko.emf.osgi.example.model.basic.BasicPackage#getGeometry_Id()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.gecko.emf.osgi.example.model.basic.Geometry#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Multi Dimensional Coordinates</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}<code>[][]</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Multi Dimensional Coordinates</em>' attribute list.
	 * @see org.gecko.emf.osgi.example.model.basic.BasicPackage#getGeometry_MultiDimensionalCoordinates()
	 * @model dataType="org.gecko.emf.osgi.example.model.basic.MultiDimensionCoordinates"
	 * @generated
	 */
	EList<Double[][]> getMultiDimensionalCoordinates();

} // Geometry
