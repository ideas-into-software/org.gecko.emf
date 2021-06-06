/**
 */
package org.geckoprojects.emf.example.model.extended.model;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.geckoprojects.emf.example.model.basic.model.BasicPackage;

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
 * @see org.geckoprojects.emf.example.model.extended.model.ExtendedPrefixFactory
 * @model kind="package"
 * @generated
 */
public interface ExtendedPrefixPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "model";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://geckoprojects.org/example/model/extendendend/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "model";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ExtendedPrefixPackage eINSTANCE = org.geckoprojects.emf.example.model.extended.model.impl.ExtendedPrefixPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.geckoprojects.emf.example.model.extended.model.impl.ExtendedAddressImpl <em>Extended Address</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.geckoprojects.emf.example.model.extended.model.impl.ExtendedAddressImpl
	 * @see org.geckoprojects.emf.example.model.extended.model.impl.ExtendedPrefixPackageImpl#getExtendedAddress()
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
	 * The number of structural features of the '<em>Extended Address</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ADDRESS_FEATURE_COUNT = BasicPackage.ADDRESS_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Extended Address</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ADDRESS_OPERATION_COUNT = BasicPackage.ADDRESS_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.geckoprojects.emf.example.model.extended.model.impl.ExtendedPersonImpl <em>Extended Person</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.geckoprojects.emf.example.model.extended.model.impl.ExtendedPersonImpl
	 * @see org.geckoprojects.emf.example.model.extended.model.impl.ExtendedPrefixPackageImpl#getExtendedPerson()
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
	 * The number of structural features of the '<em>Extended Person</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_PERSON_FEATURE_COUNT = BasicPackage.EMPLOYEE_INFO_FEATURE_COUNT + 13;

	/**
	 * The number of operations of the '<em>Extended Person</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_PERSON_OPERATION_COUNT = BasicPackage.EMPLOYEE_INFO_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.geckoprojects.emf.example.model.extended.model.ExtendedAddress <em>Extended Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extended Address</em>'.
	 * @see org.geckoprojects.emf.example.model.extended.model.ExtendedAddress
	 * @generated
	 */
	EClass getExtendedAddress();

	/**
	 * Returns the meta object for class '{@link org.geckoprojects.emf.example.model.extended.model.ExtendedPerson <em>Extended Person</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extended Person</em>'.
	 * @see org.geckoprojects.emf.example.model.extended.model.ExtendedPerson
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
	ExtendedPrefixFactory getExtendedPrefixFactory();

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
		 * The meta object literal for the '{@link org.geckoprojects.emf.example.model.extended.model.impl.ExtendedAddressImpl <em>Extended Address</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.geckoprojects.emf.example.model.extended.model.impl.ExtendedAddressImpl
		 * @see org.geckoprojects.emf.example.model.extended.model.impl.ExtendedPrefixPackageImpl#getExtendedAddress()
		 * @generated
		 */
		EClass EXTENDED_ADDRESS = eINSTANCE.getExtendedAddress();

		/**
		 * The meta object literal for the '{@link org.geckoprojects.emf.example.model.extended.model.impl.ExtendedPersonImpl <em>Extended Person</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.geckoprojects.emf.example.model.extended.model.impl.ExtendedPersonImpl
		 * @see org.geckoprojects.emf.example.model.extended.model.impl.ExtendedPrefixPackageImpl#getExtendedPerson()
		 * @generated
		 */
		EClass EXTENDED_PERSON = eINSTANCE.getExtendedPerson();

	}

} //ExtendedPrefixPackage
