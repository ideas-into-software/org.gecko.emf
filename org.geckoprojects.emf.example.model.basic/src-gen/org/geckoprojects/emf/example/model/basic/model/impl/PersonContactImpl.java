/**
 */
package org.geckoprojects.emf.example.model.basic.model.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.geckoprojects.emf.example.model.basic.model.BasicPackage;
import org.geckoprojects.emf.example.model.basic.model.Person;
import org.geckoprojects.emf.example.model.basic.model.PersonContact;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Person Contact</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.geckoprojects.emf.example.model.basic.model.impl.PersonContactImpl#getContactPerson <em>Contact Person</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PersonContactImpl extends ContactImpl implements PersonContact {
	/**
	 * The cached value of the '{@link #getContactPerson() <em>Contact Person</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContactPerson()
	 * @generated
	 * @ordered
	 */
	protected Person contactPerson;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PersonContactImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BasicPackage.Literals.PERSON_CONTACT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Person getContactPerson() {
		if (contactPerson != null && contactPerson.eIsProxy()) {
			InternalEObject oldContactPerson = (InternalEObject)contactPerson;
			contactPerson = (Person)eResolveProxy(oldContactPerson);
			if (contactPerson != oldContactPerson) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BasicPackage.PERSON_CONTACT__CONTACT_PERSON, oldContactPerson, contactPerson));
			}
		}
		return contactPerson;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Person basicGetContactPerson() {
		return contactPerson;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setContactPerson(Person newContactPerson) {
		Person oldContactPerson = contactPerson;
		contactPerson = newContactPerson;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BasicPackage.PERSON_CONTACT__CONTACT_PERSON, oldContactPerson, contactPerson));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BasicPackage.PERSON_CONTACT__CONTACT_PERSON:
				if (resolve) return getContactPerson();
				return basicGetContactPerson();
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
			case BasicPackage.PERSON_CONTACT__CONTACT_PERSON:
				setContactPerson((Person)newValue);
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
			case BasicPackage.PERSON_CONTACT__CONTACT_PERSON:
				setContactPerson((Person)null);
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
			case BasicPackage.PERSON_CONTACT__CONTACT_PERSON:
				return contactPerson != null;
		}
		return super.eIsSet(featureID);
	}

} //PersonContactImpl
