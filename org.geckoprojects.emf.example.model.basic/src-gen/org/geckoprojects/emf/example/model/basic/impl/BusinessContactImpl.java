/**
 */
package org.geckoprojects.emf.example.model.basic.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.geckoprojects.emf.example.model.basic.BasicPackage;
import org.geckoprojects.emf.example.model.basic.BusinessContact;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Business Contact</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.impl.BusinessContactImpl#getCompanyName <em>Company Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BusinessContactImpl extends ContactImpl implements BusinessContact {
	/**
	 * The default value of the '{@link #getCompanyName() <em>Company Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompanyName()
	 * @generated
	 * @ordered
	 */
	protected static final String COMPANY_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCompanyName() <em>Company Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompanyName()
	 * @generated
	 * @ordered
	 */
	protected String companyName = COMPANY_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BusinessContactImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BasicPackage.Literals.BUSINESS_CONTACT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCompanyName(String newCompanyName) {
		String oldCompanyName = companyName;
		companyName = newCompanyName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BasicPackage.BUSINESS_CONTACT__COMPANY_NAME, oldCompanyName, companyName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BasicPackage.BUSINESS_CONTACT__COMPANY_NAME:
				return getCompanyName();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case BasicPackage.BUSINESS_CONTACT__COMPANY_NAME:
				setCompanyName((String)newValue);
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
			case BasicPackage.BUSINESS_CONTACT__COMPANY_NAME:
				setCompanyName(COMPANY_NAME_EDEFAULT);
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
			case BasicPackage.BUSINESS_CONTACT__COMPANY_NAME:
				return COMPANY_NAME_EDEFAULT == null ? companyName != null : !COMPANY_NAME_EDEFAULT.equals(companyName);
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
		result.append(" (companyName: ");
		result.append(companyName);
		result.append(')');
		return result.toString();
	}

} //BusinessContactImpl
