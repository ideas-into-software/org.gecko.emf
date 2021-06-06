/**
 */
package org.geckoprojects.emf.example.model.basic.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.geckoprojects.emf.example.model.basic.model.BasicPackage;
import org.geckoprojects.emf.example.model.basic.model.BusinessPerson;
import org.geckoprojects.emf.example.model.basic.model.EmployeeInfo;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Business Person</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.model.impl.BusinessPersonImpl#getCompanyIdCardNumber <em>Company Id Card Number</em>}</li>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.model.impl.BusinessPersonImpl#getEmployeeInfo <em>Employee Info</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BusinessPersonImpl extends PersonImpl implements BusinessPerson {
	/**
	 * The default value of the '{@link #getCompanyIdCardNumber() <em>Company Id Card Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompanyIdCardNumber()
	 * @generated
	 * @ordered
	 */
	protected static final String COMPANY_ID_CARD_NUMBER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCompanyIdCardNumber() <em>Company Id Card Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompanyIdCardNumber()
	 * @generated
	 * @ordered
	 */
	protected String companyIdCardNumber = COMPANY_ID_CARD_NUMBER_EDEFAULT;

	/**
	 * The cached value of the '{@link #getEmployeeInfo() <em>Employee Info</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEmployeeInfo()
	 * @generated
	 * @ordered
	 */
	protected EList<EmployeeInfo> employeeInfo;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BusinessPersonImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BasicPackage.Literals.BUSINESS_PERSON;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCompanyIdCardNumber() {
		return companyIdCardNumber;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCompanyIdCardNumber(String newCompanyIdCardNumber) {
		String oldCompanyIdCardNumber = companyIdCardNumber;
		companyIdCardNumber = newCompanyIdCardNumber;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BasicPackage.BUSINESS_PERSON__COMPANY_ID_CARD_NUMBER, oldCompanyIdCardNumber, companyIdCardNumber));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<EmployeeInfo> getEmployeeInfo() {
		if (employeeInfo == null) {
			employeeInfo = new EObjectContainmentEList<EmployeeInfo>(EmployeeInfo.class, this, BasicPackage.BUSINESS_PERSON__EMPLOYEE_INFO);
		}
		return employeeInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BasicPackage.BUSINESS_PERSON__EMPLOYEE_INFO:
				return ((InternalEList<?>)getEmployeeInfo()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BasicPackage.BUSINESS_PERSON__COMPANY_ID_CARD_NUMBER:
				return getCompanyIdCardNumber();
			case BasicPackage.BUSINESS_PERSON__EMPLOYEE_INFO:
				return getEmployeeInfo();
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
			case BasicPackage.BUSINESS_PERSON__COMPANY_ID_CARD_NUMBER:
				setCompanyIdCardNumber((String)newValue);
				return;
			case BasicPackage.BUSINESS_PERSON__EMPLOYEE_INFO:
				getEmployeeInfo().clear();
				getEmployeeInfo().addAll((Collection<? extends EmployeeInfo>)newValue);
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
			case BasicPackage.BUSINESS_PERSON__COMPANY_ID_CARD_NUMBER:
				setCompanyIdCardNumber(COMPANY_ID_CARD_NUMBER_EDEFAULT);
				return;
			case BasicPackage.BUSINESS_PERSON__EMPLOYEE_INFO:
				getEmployeeInfo().clear();
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
			case BasicPackage.BUSINESS_PERSON__COMPANY_ID_CARD_NUMBER:
				return COMPANY_ID_CARD_NUMBER_EDEFAULT == null ? companyIdCardNumber != null : !COMPANY_ID_CARD_NUMBER_EDEFAULT.equals(companyIdCardNumber);
			case BasicPackage.BUSINESS_PERSON__EMPLOYEE_INFO:
				return employeeInfo != null && !employeeInfo.isEmpty();
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
		result.append(" (companyIdCardNumber: ");
		result.append(companyIdCardNumber);
		result.append(')');
		return result.toString();
	}

} //BusinessPersonImpl
