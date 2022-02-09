/**
 */
package org.gecko.emf.osgi.example.model.basic.impl;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.gecko.emf.osgi.example.model.basic.Address;
import org.gecko.emf.osgi.example.model.basic.BasicPackage;
import org.gecko.emf.osgi.example.model.basic.Contact;
import org.gecko.emf.osgi.example.model.basic.GenderType;
import org.gecko.emf.osgi.example.model.basic.Person;
import org.gecko.emf.osgi.example.model.basic.Tag;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Person</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gecko.emf.osgi.example.model.basic.impl.PersonImpl#getFirstName <em>First Name</em>}</li>
 *   <li>{@link org.gecko.emf.osgi.example.model.basic.impl.PersonImpl#getLastName <em>Last Name</em>}</li>
 *   <li>{@link org.gecko.emf.osgi.example.model.basic.impl.PersonImpl#getContact <em>Contact</em>}</li>
 *   <li>{@link org.gecko.emf.osgi.example.model.basic.impl.PersonImpl#getAddress <em>Address</em>}</li>
 *   <li>{@link org.gecko.emf.osgi.example.model.basic.impl.PersonImpl#getGender <em>Gender</em>}</li>
 *   <li>{@link org.gecko.emf.osgi.example.model.basic.impl.PersonImpl#getTags <em>Tags</em>}</li>
 *   <li>{@link org.gecko.emf.osgi.example.model.basic.impl.PersonImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.gecko.emf.osgi.example.model.basic.impl.PersonImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.gecko.emf.osgi.example.model.basic.impl.PersonImpl#getBigInt <em>Big Int</em>}</li>
 *   <li>{@link org.gecko.emf.osgi.example.model.basic.impl.PersonImpl#getBigDec <em>Big Dec</em>}</li>
 *   <li>{@link org.gecko.emf.osgi.example.model.basic.impl.PersonImpl#getImage <em>Image</em>}</li>
 *   <li>{@link org.gecko.emf.osgi.example.model.basic.impl.PersonImpl#getRelatives <em>Relatives</em>}</li>
 *   <li>{@link org.gecko.emf.osgi.example.model.basic.impl.PersonImpl#getTransientAddress <em>Transient Address</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PersonImpl extends MinimalEObjectImpl.Container implements Person {
	/**
	 * The default value of the '{@link #getFirstName() <em>First Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFirstName()
	 * @generated
	 * @ordered
	 */
	protected static final String FIRST_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFirstName() <em>First Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFirstName()
	 * @generated
	 * @ordered
	 */
	protected String firstName = FIRST_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getLastName() <em>Last Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLastName()
	 * @generated
	 * @ordered
	 */
	protected static final String LAST_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLastName() <em>Last Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLastName()
	 * @generated
	 * @ordered
	 */
	protected String lastName = LAST_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getContact() <em>Contact</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContact()
	 * @generated
	 * @ordered
	 */
	protected EList<Contact> contact;

	/**
	 * The cached value of the '{@link #getAddress() <em>Address</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAddress()
	 * @generated
	 * @ordered
	 */
	protected Address address;

	/**
	 * The default value of the '{@link #getGender() <em>Gender</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGender()
	 * @generated
	 * @ordered
	 */
	protected static final GenderType GENDER_EDEFAULT = GenderType.FEMALE;

	/**
	 * The cached value of the '{@link #getGender() <em>Gender</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGender()
	 * @generated
	 * @ordered
	 */
	protected GenderType gender = GENDER_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTags() <em>Tags</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTags()
	 * @generated
	 * @ordered
	 */
	protected EList<Tag> tags;

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
	 * The cached value of the '{@link #getProperties() <em>Properties</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperties()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> properties;

	/**
	 * The default value of the '{@link #getBigInt() <em>Big Int</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBigInt()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger BIG_INT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBigInt() <em>Big Int</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBigInt()
	 * @generated
	 * @ordered
	 */
	protected BigInteger bigInt = BIG_INT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getBigDec() <em>Big Dec</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBigDec()
	 * @generated
	 * @ordered
	 */
	protected EList<BigDecimal> bigDec;

	/**
	 * The default value of the '{@link #getImage() <em>Image</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImage()
	 * @generated
	 * @ordered
	 */
	protected static final byte[] IMAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getImage() <em>Image</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImage()
	 * @generated
	 * @ordered
	 */
	protected byte[] image = IMAGE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRelatives() <em>Relatives</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelatives()
	 * @generated
	 * @ordered
	 */
	protected EList<Person> relatives;

	/**
	 * The cached value of the '{@link #getTransientAddress() <em>Transient Address</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransientAddress()
	 * @generated
	 * @ordered
	 */
	protected Address transientAddress;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PersonImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BasicPackage.Literals.PERSON;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getFirstName() {
		return firstName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFirstName(String newFirstName) {
		String oldFirstName = firstName;
		firstName = newFirstName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BasicPackage.PERSON__FIRST_NAME, oldFirstName, firstName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLastName() {
		return lastName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLastName(String newLastName) {
		String oldLastName = lastName;
		lastName = newLastName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BasicPackage.PERSON__LAST_NAME, oldLastName, lastName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Contact> getContact() {
		if (contact == null) {
			contact = new EObjectContainmentEList<Contact>(Contact.class, this, BasicPackage.PERSON__CONTACT);
		}
		return contact;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Address getAddress() {
		if (address != null && address.eIsProxy()) {
			InternalEObject oldAddress = (InternalEObject)address;
			address = (Address)eResolveProxy(oldAddress);
			if (address != oldAddress) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BasicPackage.PERSON__ADDRESS, oldAddress, address));
			}
		}
		return address;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Address basicGetAddress() {
		return address;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAddress(Address newAddress) {
		Address oldAddress = address;
		address = newAddress;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BasicPackage.PERSON__ADDRESS, oldAddress, address));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GenderType getGender() {
		return gender;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setGender(GenderType newGender) {
		GenderType oldGender = gender;
		gender = newGender == null ? GENDER_EDEFAULT : newGender;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BasicPackage.PERSON__GENDER, oldGender, gender));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Tag> getTags() {
		if (tags == null) {
			tags = new EObjectContainmentEList<Tag>(Tag.class, this, BasicPackage.PERSON__TAGS);
		}
		return tags;
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
			eNotify(new ENotificationImpl(this, Notification.SET, BasicPackage.PERSON__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EMap<String, String> getProperties() {
		if (properties == null) {
			properties = new EcoreEMap<String,String>(BasicPackage.Literals.STRING_STRING_MAP, StringStringMapImpl.class, this, BasicPackage.PERSON__PROPERTIES);
		}
		return properties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BigInteger getBigInt() {
		return bigInt;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBigInt(BigInteger newBigInt) {
		BigInteger oldBigInt = bigInt;
		bigInt = newBigInt;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BasicPackage.PERSON__BIG_INT, oldBigInt, bigInt));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<BigDecimal> getBigDec() {
		if (bigDec == null) {
			bigDec = new EDataTypeUniqueEList<BigDecimal>(BigDecimal.class, this, BasicPackage.PERSON__BIG_DEC);
		}
		return bigDec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public byte[] getImage() {
		return image;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setImage(byte[] newImage) {
		byte[] oldImage = image;
		image = newImage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BasicPackage.PERSON__IMAGE, oldImage, image));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Person> getRelatives() {
		if (relatives == null) {
			relatives = new EObjectResolvingEList<Person>(Person.class, this, BasicPackage.PERSON__RELATIVES);
		}
		return relatives;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Address getTransientAddress() {
		if (transientAddress != null && transientAddress.eIsProxy()) {
			InternalEObject oldTransientAddress = (InternalEObject)transientAddress;
			transientAddress = (Address)eResolveProxy(oldTransientAddress);
			if (transientAddress != oldTransientAddress) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BasicPackage.PERSON__TRANSIENT_ADDRESS, oldTransientAddress, transientAddress));
			}
		}
		return transientAddress;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Address basicGetTransientAddress() {
		return transientAddress;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTransientAddress(Address newTransientAddress) {
		Address oldTransientAddress = transientAddress;
		transientAddress = newTransientAddress;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BasicPackage.PERSON__TRANSIENT_ADDRESS, oldTransientAddress, transientAddress));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BasicPackage.PERSON__CONTACT:
				return ((InternalEList<?>)getContact()).basicRemove(otherEnd, msgs);
			case BasicPackage.PERSON__TAGS:
				return ((InternalEList<?>)getTags()).basicRemove(otherEnd, msgs);
			case BasicPackage.PERSON__PROPERTIES:
				return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
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
			case BasicPackage.PERSON__FIRST_NAME:
				return getFirstName();
			case BasicPackage.PERSON__LAST_NAME:
				return getLastName();
			case BasicPackage.PERSON__CONTACT:
				return getContact();
			case BasicPackage.PERSON__ADDRESS:
				if (resolve) return getAddress();
				return basicGetAddress();
			case BasicPackage.PERSON__GENDER:
				return getGender();
			case BasicPackage.PERSON__TAGS:
				return getTags();
			case BasicPackage.PERSON__ID:
				return getId();
			case BasicPackage.PERSON__PROPERTIES:
				if (coreType) return getProperties();
				else return getProperties().map();
			case BasicPackage.PERSON__BIG_INT:
				return getBigInt();
			case BasicPackage.PERSON__BIG_DEC:
				return getBigDec();
			case BasicPackage.PERSON__IMAGE:
				return getImage();
			case BasicPackage.PERSON__RELATIVES:
				return getRelatives();
			case BasicPackage.PERSON__TRANSIENT_ADDRESS:
				if (resolve) return getTransientAddress();
				return basicGetTransientAddress();
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
			case BasicPackage.PERSON__FIRST_NAME:
				setFirstName((String)newValue);
				return;
			case BasicPackage.PERSON__LAST_NAME:
				setLastName((String)newValue);
				return;
			case BasicPackage.PERSON__CONTACT:
				getContact().clear();
				getContact().addAll((Collection<? extends Contact>)newValue);
				return;
			case BasicPackage.PERSON__ADDRESS:
				setAddress((Address)newValue);
				return;
			case BasicPackage.PERSON__GENDER:
				setGender((GenderType)newValue);
				return;
			case BasicPackage.PERSON__TAGS:
				getTags().clear();
				getTags().addAll((Collection<? extends Tag>)newValue);
				return;
			case BasicPackage.PERSON__ID:
				setId((String)newValue);
				return;
			case BasicPackage.PERSON__PROPERTIES:
				((EStructuralFeature.Setting)getProperties()).set(newValue);
				return;
			case BasicPackage.PERSON__BIG_INT:
				setBigInt((BigInteger)newValue);
				return;
			case BasicPackage.PERSON__BIG_DEC:
				getBigDec().clear();
				getBigDec().addAll((Collection<? extends BigDecimal>)newValue);
				return;
			case BasicPackage.PERSON__IMAGE:
				setImage((byte[])newValue);
				return;
			case BasicPackage.PERSON__RELATIVES:
				getRelatives().clear();
				getRelatives().addAll((Collection<? extends Person>)newValue);
				return;
			case BasicPackage.PERSON__TRANSIENT_ADDRESS:
				setTransientAddress((Address)newValue);
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
			case BasicPackage.PERSON__FIRST_NAME:
				setFirstName(FIRST_NAME_EDEFAULT);
				return;
			case BasicPackage.PERSON__LAST_NAME:
				setLastName(LAST_NAME_EDEFAULT);
				return;
			case BasicPackage.PERSON__CONTACT:
				getContact().clear();
				return;
			case BasicPackage.PERSON__ADDRESS:
				setAddress((Address)null);
				return;
			case BasicPackage.PERSON__GENDER:
				setGender(GENDER_EDEFAULT);
				return;
			case BasicPackage.PERSON__TAGS:
				getTags().clear();
				return;
			case BasicPackage.PERSON__ID:
				setId(ID_EDEFAULT);
				return;
			case BasicPackage.PERSON__PROPERTIES:
				getProperties().clear();
				return;
			case BasicPackage.PERSON__BIG_INT:
				setBigInt(BIG_INT_EDEFAULT);
				return;
			case BasicPackage.PERSON__BIG_DEC:
				getBigDec().clear();
				return;
			case BasicPackage.PERSON__IMAGE:
				setImage(IMAGE_EDEFAULT);
				return;
			case BasicPackage.PERSON__RELATIVES:
				getRelatives().clear();
				return;
			case BasicPackage.PERSON__TRANSIENT_ADDRESS:
				setTransientAddress((Address)null);
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
			case BasicPackage.PERSON__FIRST_NAME:
				return FIRST_NAME_EDEFAULT == null ? firstName != null : !FIRST_NAME_EDEFAULT.equals(firstName);
			case BasicPackage.PERSON__LAST_NAME:
				return LAST_NAME_EDEFAULT == null ? lastName != null : !LAST_NAME_EDEFAULT.equals(lastName);
			case BasicPackage.PERSON__CONTACT:
				return contact != null && !contact.isEmpty();
			case BasicPackage.PERSON__ADDRESS:
				return address != null;
			case BasicPackage.PERSON__GENDER:
				return gender != GENDER_EDEFAULT;
			case BasicPackage.PERSON__TAGS:
				return tags != null && !tags.isEmpty();
			case BasicPackage.PERSON__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case BasicPackage.PERSON__PROPERTIES:
				return properties != null && !properties.isEmpty();
			case BasicPackage.PERSON__BIG_INT:
				return BIG_INT_EDEFAULT == null ? bigInt != null : !BIG_INT_EDEFAULT.equals(bigInt);
			case BasicPackage.PERSON__BIG_DEC:
				return bigDec != null && !bigDec.isEmpty();
			case BasicPackage.PERSON__IMAGE:
				return IMAGE_EDEFAULT == null ? image != null : !IMAGE_EDEFAULT.equals(image);
			case BasicPackage.PERSON__RELATIVES:
				return relatives != null && !relatives.isEmpty();
			case BasicPackage.PERSON__TRANSIENT_ADDRESS:
				return transientAddress != null;
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
		result.append(" (firstName: ");
		result.append(firstName);
		result.append(", lastName: ");
		result.append(lastName);
		result.append(", gender: ");
		result.append(gender);
		result.append(", id: ");
		result.append(id);
		result.append(", bigInt: ");
		result.append(bigInt);
		result.append(", bigDec: ");
		result.append(bigDec);
		result.append(", image: ");
		result.append(image);
		result.append(')');
		return result.toString();
	}

} //PersonImpl
