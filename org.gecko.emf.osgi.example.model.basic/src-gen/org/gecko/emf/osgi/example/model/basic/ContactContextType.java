/**
 */
package org.gecko.emf.osgi.example.model.basic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Contact Context Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.gecko.emf.osgi.example.model.basic.BasicPackage#getContactContextType()
 * @model
 * @generated
 */
public enum ContactContextType implements Enumerator {
	/**
	 * The '<em><b>HOME</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HOME_VALUE
	 * @generated
	 * @ordered
	 */
	HOME(0, "HOME", "HOME"),

	/**
	 * The '<em><b>PRIVATE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PRIVATE_VALUE
	 * @generated
	 * @ordered
	 */
	PRIVATE(1, "PRIVATE", "PRIVATE"),

	/**
	 * The '<em><b>WORK</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WORK_VALUE
	 * @generated
	 * @ordered
	 */
	WORK(2, "WORK", "WORK"),

	/**
	 * The '<em><b>TEST</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TEST_VALUE
	 * @generated
	 * @ordered
	 */
	TEST(3, "T_E_S_T", "T_E_S_T");

	/**
	 * The '<em><b>HOME</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HOME
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int HOME_VALUE = 0;

	/**
	 * The '<em><b>PRIVATE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PRIVATE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PRIVATE_VALUE = 1;

	/**
	 * The '<em><b>WORK</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WORK
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int WORK_VALUE = 2;

	/**
	 * The '<em><b>TEST</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TEST
	 * @model name="T_E_S_T"
	 * @generated
	 * @ordered
	 */
	public static final int TEST_VALUE = 3;

	/**
	 * An array of all the '<em><b>Contact Context Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ContactContextType[] VALUES_ARRAY =
		new ContactContextType[] {
			HOME,
			PRIVATE,
			WORK,
			TEST,
		};

	/**
	 * A public read-only list of all the '<em><b>Contact Context Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ContactContextType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Contact Context Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ContactContextType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ContactContextType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Contact Context Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ContactContextType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ContactContextType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Contact Context Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ContactContextType get(int value) {
		switch (value) {
			case HOME_VALUE: return HOME;
			case PRIVATE_VALUE: return PRIVATE;
			case WORK_VALUE: return WORK;
			case TEST_VALUE: return TEST;
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
	private ContactContextType(int value, String name, String literal) {
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
	
} //ContactContextType
