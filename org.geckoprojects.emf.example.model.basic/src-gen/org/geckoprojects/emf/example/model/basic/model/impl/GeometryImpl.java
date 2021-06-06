/**
 */
package org.geckoprojects.emf.example.model.basic.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

import org.geckoprojects.emf.example.model.basic.model.BasicPackage;
import org.geckoprojects.emf.example.model.basic.model.Geometry;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Geometry</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.model.impl.GeometryImpl#getCoordinates <em>Coordinates</em>}</li>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.model.impl.GeometryImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.model.impl.GeometryImpl#getMultiDimensionalCoordinates <em>Multi Dimensional Coordinates</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GeometryImpl extends MinimalEObjectImpl.Container implements Geometry {
	/**
	 * The cached value of the '{@link #getCoordinates() <em>Coordinates</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoordinates()
	 * @generated
	 * @ordered
	 */
	protected EList<Double[]> coordinates;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMultiDimensionalCoordinates() <em>Multi Dimensional Coordinates</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMultiDimensionalCoordinates()
	 * @generated
	 * @ordered
	 */
	protected EList<Double[][]> multiDimensionalCoordinates;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GeometryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BasicPackage.Literals.GEOMETRY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Double[]> getCoordinates() {
		if (coordinates == null) {
			coordinates = new EDataTypeUniqueEList<Double[]>(Double[].class, this, BasicPackage.GEOMETRY__COORDINATES);
		}
		return coordinates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BasicPackage.GEOMETRY__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Double[][]> getMultiDimensionalCoordinates() {
		if (multiDimensionalCoordinates == null) {
			multiDimensionalCoordinates = new EDataTypeUniqueEList<Double[][]>(Double[][].class, this, BasicPackage.GEOMETRY__MULTI_DIMENSIONAL_COORDINATES);
		}
		return multiDimensionalCoordinates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BasicPackage.GEOMETRY__COORDINATES:
				return getCoordinates();
			case BasicPackage.GEOMETRY__ID:
				return getId();
			case BasicPackage.GEOMETRY__MULTI_DIMENSIONAL_COORDINATES:
				return getMultiDimensionalCoordinates();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case BasicPackage.GEOMETRY__COORDINATES:
				getCoordinates().clear();
				getCoordinates().addAll((Collection<? extends Double[]>)newValue);
				return;
			case BasicPackage.GEOMETRY__ID:
				setId((String)newValue);
				return;
			case BasicPackage.GEOMETRY__MULTI_DIMENSIONAL_COORDINATES:
				getMultiDimensionalCoordinates().clear();
				getMultiDimensionalCoordinates().addAll((Collection<? extends Double[][]>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case BasicPackage.GEOMETRY__COORDINATES:
				getCoordinates().clear();
				return;
			case BasicPackage.GEOMETRY__ID:
				setId(ID_EDEFAULT);
				return;
			case BasicPackage.GEOMETRY__MULTI_DIMENSIONAL_COORDINATES:
				getMultiDimensionalCoordinates().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case BasicPackage.GEOMETRY__COORDINATES:
				return coordinates != null && !coordinates.isEmpty();
			case BasicPackage.GEOMETRY__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case BasicPackage.GEOMETRY__MULTI_DIMENSIONAL_COORDINATES:
				return multiDimensionalCoordinates != null && !multiDimensionalCoordinates.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (coordinates: ");
		result.append(coordinates);
		result.append(", id: ");
		result.append(id);
		result.append(", multiDimensionalCoordinates: ");
		result.append(multiDimensionalCoordinates);
		result.append(')');
		return result.toString();
	}

} //GeometryImpl
