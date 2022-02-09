/**
 */
package org.gecko.emf.osgi.example.model.basic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Contact Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.gecko.emf.osgi.example.model.basic.BasicPackage#getContactType()
 * @model
 * @generated
 */
public enum ContactType implements Enumerator {
	/**
	 * The '<em><b>PHONE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PHONE_VALUE
	 * @generated
	 * @ordered
	 */
	PHONE(0, "PHONE", "Phone"),

	/**
	 * The '<em><b>MOBILE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MOBILE_VALUE
	 * @generated
	 * @ordered
	 */
	MOBILE(1, "MOBILE", "Mobile"),

	/**
	 * The '<em><b>WHATSAPP</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WHATSAPP_VALUE
	 * @generated
	 * @ordered
	 */
	WHATSAPP(2, "WHATSAPP", "WhatsApp"),

	/**
	 * The '<em><b>EMAIL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EMAIL_VALUE
	 * @generated
	 * @ordered
	 */
	EMAIL(3, "EMAIL", "Email"),

	/**
	 * The '<em><b>SKYPE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SKYPE_VALUE
	 * @generated
	 * @ordered
	 */
	SKYPE(4, "SKYPE", "Skype"),

	/**
	 * The '<em><b>WEBADDRESS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WEBADDRESS_VALUE
	 * @generated
	 * @ordered
	 */
	WEBADDRESS(5, "WEBADDRESS", "WWW");

	/**
	 * The '<em><b>PHONE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PHONE
	 * @model literal="Phone"
	 * @generated
	 * @ordered
	 */
	public static final int PHONE_VALUE = 0;

	/**
	 * The '<em><b>MOBILE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MOBILE
	 * @model literal="Mobile"
	 * @generated
	 * @ordered
	 */
	public static final int MOBILE_VALUE = 1;

	/**
	 * The '<em><b>WHATSAPP</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WHATSAPP
	 * @model literal="WhatsApp"
	 * @generated
	 * @ordered
	 */
	public static final int WHATSAPP_VALUE = 2;

	/**
	 * The '<em><b>EMAIL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EMAIL
	 * @model literal="Email"
	 * @generated
	 * @ordered
	 */
	public static final int EMAIL_VALUE = 3;

	/**
	 * The '<em><b>SKYPE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SKYPE
	 * @model literal="Skype"
	 * @generated
	 * @ordered
	 */
	public static final int SKYPE_VALUE = 4;

	/**
	 * The '<em><b>WEBADDRESS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WEBADDRESS
	 * @model literal="WWW"
	 * @generated
	 * @ordered
	 */
	public static final int WEBADDRESS_VALUE = 5;

	/**
	 * An array of all the '<em><b>Contact Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ContactType[] VALUES_ARRAY =
		new ContactType[] {
			PHONE,
			MOBILE,
			WHATSAPP,
			EMAIL,
			SKYPE,
			WEBADDRESS,
		};

	/**
	 * A public read-only list of all the '<em><b>Contact Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ContactType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Contact Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ContactType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ContactType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Contact Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ContactType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ContactType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Contact Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ContactType get(int value) {
		switch (value) {
			case PHONE_VALUE: return PHONE;
			case MOBILE_VALUE: return MOBILE;
			case WHATSAPP_VALUE: return WHATSAPP;
			case EMAIL_VALUE: return EMAIL;
			case SKYPE_VALUE: return SKYPE;
			case WEBADDRESS_VALUE: return WEBADDRESS;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ContactType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //ContactType
