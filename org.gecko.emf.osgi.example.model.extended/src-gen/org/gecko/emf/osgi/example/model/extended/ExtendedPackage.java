/**
 */
package org.gecko.emf.osgi.example.model.extended;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.gecko.emf.osgi.example.model.basic.BasicPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.gecko.emf.osgi.example.model.extended.ExtendedFactory
 * @model kind="package"
 * @generated
 */
public interface ExtendedPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "extended";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://gecko.org/example/model/extendendend/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "extended";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ExtendedPackage eINSTANCE = org.gecko.emf.osgi.example.model.extended.impl.ExtendedPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.gecko.emf.osgi.example.model.extended.impl.ExtendedAddressImpl <em>Address</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.emf.osgi.example.model.extended.impl.ExtendedAddressImpl
	 * @see org.gecko.emf.osgi.example.model.extended.impl.ExtendedPackageImpl#getExtendedAddress()
	 * @generated
	 */
	int EXTENDED_ADDRESS = 0;

	/**
	 * The feature id for the '<em><b>Street</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ADDRESS__STREET = BasicPackage.ADDRESS__STREET;

	/**
	 * The feature id for the '<em><b>City</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ADDRESS__CITY = BasicPackage.ADDRESS__CITY;

	/**
	 * The feature id for the '<em><b>Zip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ADDRESS__ZIP = BasicPackage.ADDRESS__ZIP;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ADDRESS__ID = BasicPackage.ADDRESS__ID;

	/**
	 * The feature id for the '<em><b>Npe</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ADDRESS__NPE = BasicPackage.ADDRESS__NPE;

	/**
	 * The number of structural features of the '<em>Address</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ADDRESS_FEATURE_COUNT = BasicPackage.ADDRESS_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Address</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ADDRESS_OPERATION_COUNT = BasicPackage.ADDRESS_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gecko.emf.osgi.example.model.extended.impl.ExtendedPersonImpl <em>Person</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gecko.emf.osgi.example.model.extended.impl.ExtendedPersonImpl
	 * @see org.gecko.emf.osgi.example.model.extended.impl.ExtendedPackageImpl#getExtendedPerson()
	 * @generated
	 */
	int EXTENDED_PERSON = 1;

	/**
	 * The feature id for the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_PERSON__POSITION = BasicPackage.EMPLOYEE_INFO__POSITION;

	/**
	 * The feature id for the '<em><b>First Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_PERSON__FIRST_NAME = BasicPackage.EMPLOYEE_INFO_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Last Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_PERSON__LAST_NAME = BasicPackage.EMPLOYEE_INFO_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Contact</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_PERSON__CONTACT = BasicPackage.EMPLOYEE_INFO_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Address</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_PERSON__ADDRESS = BasicPackage.EMPLOYEE_INFO_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Gender</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_PERSON__GENDER = BasicPackage.EMPLOYEE_INFO_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_PERSON__TAGS = BasicPackage.EMPLOYEE_INFO_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_PERSON__ID = BasicPackage.EMPLOYEE_INFO_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_PERSON__PROPERTIES = BasicPackage.EMPLOYEE_INFO_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Big Int</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_PERSON__BIG_INT = BasicPackage.EMPLOYEE_INFO_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Big Dec</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_PERSON__BIG_DEC = BasicPackage.EMPLOYEE_INFO_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Image</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_PERSON__IMAGE = BasicPackage.EMPLOYEE_INFO_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Relatives</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_PERSON__RELATIVES = BasicPackage.EMPLOYEE_INFO_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Transient Address</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_PERSON__TRANSIENT_ADDRESS = BasicPackage.EMPLOYEE_INFO_FEATURE_COUNT + 12;

	/**
	 * The number of structural features of the '<em>Person</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_PERSON_FEATURE_COUNT = BasicPackage.EMPLOYEE_INFO_FEATURE_COUNT + 13;

	/**
	 * The number of operations of the '<em>Person</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_PERSON_OPERATION_COUNT = BasicPackage.EMPLOYEE_INFO_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.gecko.emf.osgi.example.model.extended.ExtendedAddress <em>Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Address</em>'.
	 * @see org.gecko.emf.osgi.example.model.extended.ExtendedAddress
	 * @generated
	 */
	EClass getExtendedAddress();

	/**
	 * Returns the meta object for class '{@link org.gecko.emf.osgi.example.model.extended.ExtendedPerson <em>Person</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Person</em>'.
	 * @see org.gecko.emf.osgi.example.model.extended.ExtendedPerson
	 * @generated
	 */
	EClass getExtendedPerson();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ExtendedFactory getExtendedFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.gecko.emf.osgi.example.model.extended.impl.ExtendedAddressImpl <em>Address</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.emf.osgi.example.model.extended.impl.ExtendedAddressImpl
		 * @see org.gecko.emf.osgi.example.model.extended.impl.ExtendedPackageImpl#getExtendedAddress()
		 * @generated
		 */
		EClass EXTENDED_ADDRESS = eINSTANCE.getExtendedAddress();

		/**
		 * The meta object literal for the '{@link org.gecko.emf.osgi.example.model.extended.impl.ExtendedPersonImpl <em>Person</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gecko.emf.osgi.example.model.extended.impl.ExtendedPersonImpl
		 * @see org.gecko.emf.osgi.example.model.extended.impl.ExtendedPackageImpl#getExtendedPerson()
		 * @generated
		 */
		EClass EXTENDED_PERSON = eINSTANCE.getExtendedPerson();

	}

} //ExtendedPackage
