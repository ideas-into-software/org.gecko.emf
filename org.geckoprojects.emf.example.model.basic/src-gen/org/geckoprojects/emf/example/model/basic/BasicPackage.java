/**
 */
package org.geckoprojects.emf.example.model.basic;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see org.geckoprojects.emf.example.model.basic.BasicFactory
 * @model kind="package"
 * @generated
 */
public interface BasicPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "basic";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://geckoprojects.org/example/model/basic";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "basic";

	/**
	 * The package content type ID.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eCONTENT_TYPE = "basic#1.0";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BasicPackage eINSTANCE = org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.geckoprojects.emf.example.model.basic.impl.PersonImpl <em>Person</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.geckoprojects.emf.example.model.basic.impl.PersonImpl
	 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getPerson()
	 * @generated
	 */
	int PERSON = 0;

	/**
	 * The feature id for the '<em><b>First Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__FIRST_NAME = 0;

	/**
	 * The feature id for the '<em><b>Last Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__LAST_NAME = 1;

	/**
	 * The feature id for the '<em><b>Contact</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__CONTACT = 2;

	/**
	 * The feature id for the '<em><b>Address</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__ADDRESS = 3;

	/**
	 * The feature id for the '<em><b>Gender</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__GENDER = 4;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__TAGS = 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__ID = 6;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__PROPERTIES = 7;

	/**
	 * The feature id for the '<em><b>Big Int</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__BIG_INT = 8;

	/**
	 * The feature id for the '<em><b>Big Dec</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__BIG_DEC = 9;

	/**
	 * The feature id for the '<em><b>Image</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__IMAGE = 10;

	/**
	 * The feature id for the '<em><b>Relatives</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__RELATIVES = 11;

	/**
	 * The feature id for the '<em><b>Transient Address</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__TRANSIENT_ADDRESS = 12;

	/**
	 * The number of structural features of the '<em>Person</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_FEATURE_COUNT = 13;

	/**
	 * The number of operations of the '<em>Person</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.geckoprojects.emf.example.model.basic.impl.AddressImpl <em>Address</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.geckoprojects.emf.example.model.basic.impl.AddressImpl
	 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getAddress()
	 * @generated
	 */
	int ADDRESS = 1;

	/**
	 * The feature id for the '<em><b>Street</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDRESS__STREET = 0;

	/**
	 * The feature id for the '<em><b>City</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDRESS__CITY = 1;

	/**
	 * The feature id for the '<em><b>Zip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDRESS__ZIP = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDRESS__ID = 3;

	/**
	 * The feature id for the '<em><b>Npe</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDRESS__NPE = 4;

	/**
	 * The number of structural features of the '<em>Address</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDRESS_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Address</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDRESS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.geckoprojects.emf.example.model.basic.impl.ContactImpl <em>Contact</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.geckoprojects.emf.example.model.basic.impl.ContactImpl
	 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getContact()
	 * @generated
	 */
	int CONTACT = 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT__TYPE = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT__VALUE = 1;

	/**
	 * The feature id for the '<em><b>Context</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT__CONTEXT = 2;

	/**
	 * The number of structural features of the '<em>Contact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Contact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.geckoprojects.emf.example.model.basic.impl.FamilyImpl <em>Family</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.geckoprojects.emf.example.model.basic.impl.FamilyImpl
	 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getFamily()
	 * @generated
	 */
	int FAMILY = 3;

	/**
	 * The feature id for the '<em><b>Father</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAMILY__FATHER = 0;

	/**
	 * The feature id for the '<em><b>Mother</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAMILY__MOTHER = 1;

	/**
	 * The feature id for the '<em><b>Children</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAMILY__CHILDREN = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAMILY__ID = 3;

	/**
	 * The number of structural features of the '<em>Family</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAMILY_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Family</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAMILY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.geckoprojects.emf.example.model.basic.impl.BusinessContactImpl <em>Business Contact</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.geckoprojects.emf.example.model.basic.impl.BusinessContactImpl
	 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getBusinessContact()
	 * @generated
	 */
	int BUSINESS_CONTACT = 4;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_CONTACT__TYPE = CONTACT__TYPE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_CONTACT__VALUE = CONTACT__VALUE;

	/**
	 * The feature id for the '<em><b>Context</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_CONTACT__CONTEXT = CONTACT__CONTEXT;

	/**
	 * The feature id for the '<em><b>Company Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_CONTACT__COMPANY_NAME = CONTACT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Business Contact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_CONTACT_FEATURE_COUNT = CONTACT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Business Contact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_CONTACT_OPERATION_COUNT = CONTACT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.geckoprojects.emf.example.model.basic.impl.TagImpl <em>Tag</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.geckoprojects.emf.example.model.basic.impl.TagImpl
	 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getTag()
	 * @generated
	 */
	int TAG = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__NAME = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__VALUE = 1;

	/**
	 * The feature id for the '<em><b>Tag</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__TAG = 2;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__TAGS = 3;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__DESCRIPTION = 4;

	/**
	 * The number of structural features of the '<em>Tag</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Tag</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.geckoprojects.emf.example.model.basic.impl.BusinessPersonImpl <em>Business Person</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.geckoprojects.emf.example.model.basic.impl.BusinessPersonImpl
	 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getBusinessPerson()
	 * @generated
	 */
	int BUSINESS_PERSON = 6;

	/**
	 * The feature id for the '<em><b>First Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_PERSON__FIRST_NAME = PERSON__FIRST_NAME;

	/**
	 * The feature id for the '<em><b>Last Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_PERSON__LAST_NAME = PERSON__LAST_NAME;

	/**
	 * The feature id for the '<em><b>Contact</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_PERSON__CONTACT = PERSON__CONTACT;

	/**
	 * The feature id for the '<em><b>Address</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_PERSON__ADDRESS = PERSON__ADDRESS;

	/**
	 * The feature id for the '<em><b>Gender</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_PERSON__GENDER = PERSON__GENDER;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_PERSON__TAGS = PERSON__TAGS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_PERSON__ID = PERSON__ID;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_PERSON__PROPERTIES = PERSON__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Big Int</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_PERSON__BIG_INT = PERSON__BIG_INT;

	/**
	 * The feature id for the '<em><b>Big Dec</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_PERSON__BIG_DEC = PERSON__BIG_DEC;

	/**
	 * The feature id for the '<em><b>Image</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_PERSON__IMAGE = PERSON__IMAGE;

	/**
	 * The feature id for the '<em><b>Relatives</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_PERSON__RELATIVES = PERSON__RELATIVES;

	/**
	 * The feature id for the '<em><b>Transient Address</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_PERSON__TRANSIENT_ADDRESS = PERSON__TRANSIENT_ADDRESS;

	/**
	 * The feature id for the '<em><b>Company Id Card Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_PERSON__COMPANY_ID_CARD_NUMBER = PERSON_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Employee Info</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_PERSON__EMPLOYEE_INFO = PERSON_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Business Person</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_PERSON_FEATURE_COUNT = PERSON_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Business Person</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_PERSON_OPERATION_COUNT = PERSON_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.geckoprojects.emf.example.model.basic.impl.StringStringMapImpl <em>String String Map</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.geckoprojects.emf.example.model.basic.impl.StringStringMapImpl
	 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getStringStringMap()
	 * @generated
	 */
	int STRING_STRING_MAP = 7;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_STRING_MAP__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_STRING_MAP__VALUE = 1;

	/**
	 * The number of structural features of the '<em>String String Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_STRING_MAP_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>String String Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_STRING_MAP_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.geckoprojects.emf.example.model.basic.impl.EmployeeInfoImpl <em>Employee Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.geckoprojects.emf.example.model.basic.impl.EmployeeInfoImpl
	 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getEmployeeInfo()
	 * @generated
	 */
	int EMPLOYEE_INFO = 8;

	/**
	 * The feature id for the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPLOYEE_INFO__POSITION = 0;

	/**
	 * The number of structural features of the '<em>Employee Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPLOYEE_INFO_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Employee Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPLOYEE_INFO_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.geckoprojects.emf.example.model.basic.impl.WidgetImpl <em>Widget</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.geckoprojects.emf.example.model.basic.impl.WidgetImpl
	 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getWidget()
	 * @generated
	 */
	int WIDGET = 9;

	/**
	 * The feature id for the '<em><b>Content</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIDGET__CONTENT = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIDGET__ID = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIDGET__NAME = 2;

	/**
	 * The number of structural features of the '<em>Widget</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIDGET_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Widget</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIDGET_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.geckoprojects.emf.example.model.basic.impl.ContentImpl <em>Content</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.geckoprojects.emf.example.model.basic.impl.ContentImpl
	 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getContent()
	 * @generated
	 */
	int CONTENT = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT__NAME = 0;

	/**
	 * The number of structural features of the '<em>Content</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Content</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.geckoprojects.emf.example.model.basic.impl.TextwidgetImpl <em>Textwidget</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.geckoprojects.emf.example.model.basic.impl.TextwidgetImpl
	 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getTextwidget()
	 * @generated
	 */
	int TEXTWIDGET = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXTWIDGET__NAME = CONTENT__NAME;

	/**
	 * The number of structural features of the '<em>Textwidget</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXTWIDGET_FEATURE_COUNT = CONTENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Textwidget</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXTWIDGET_OPERATION_COUNT = CONTENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.geckoprojects.emf.example.model.basic.impl.HLWidgetImpl <em>HL Widget</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.geckoprojects.emf.example.model.basic.impl.HLWidgetImpl
	 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getHLWidget()
	 * @generated
	 */
	int HL_WIDGET = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HL_WIDGET__NAME = CONTENT__NAME;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HL_WIDGET__CHILDREN = CONTENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>HL Widget</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HL_WIDGET_FEATURE_COUNT = CONTENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>HL Widget</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HL_WIDGET_OPERATION_COUNT = CONTENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.geckoprojects.emf.example.model.basic.impl.PersonContactImpl <em>Person Contact</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.geckoprojects.emf.example.model.basic.impl.PersonContactImpl
	 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getPersonContact()
	 * @generated
	 */
	int PERSON_CONTACT = 13;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_CONTACT__TYPE = CONTACT__TYPE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_CONTACT__VALUE = CONTACT__VALUE;

	/**
	 * The feature id for the '<em><b>Context</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_CONTACT__CONTEXT = CONTACT__CONTEXT;

	/**
	 * The feature id for the '<em><b>Contact Person</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_CONTACT__CONTACT_PERSON = CONTACT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Person Contact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_CONTACT_FEATURE_COUNT = CONTACT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Person Contact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_CONTACT_OPERATION_COUNT = CONTACT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.geckoprojects.emf.example.model.basic.impl.PersonObjectImpl <em>Person Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.geckoprojects.emf.example.model.basic.impl.PersonObjectImpl
	 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getPersonObject()
	 * @generated
	 */
	int PERSON_OBJECT = 14;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_OBJECT__TYPE = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_OBJECT__ID = 1;

	/**
	 * The number of structural features of the '<em>Person Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_OBJECT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Person Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_OBJECT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.geckoprojects.emf.example.model.basic.impl.GeometryImpl <em>Geometry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.geckoprojects.emf.example.model.basic.impl.GeometryImpl
	 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getGeometry()
	 * @generated
	 */
	int GEOMETRY = 15;

	/**
	 * The feature id for the '<em><b>Coordinates</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEOMETRY__COORDINATES = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEOMETRY__ID = 1;

	/**
	 * The feature id for the '<em><b>Multi Dimensional Coordinates</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEOMETRY__MULTI_DIMENSIONAL_COORDINATES = 2;

	/**
	 * The number of structural features of the '<em>Geometry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEOMETRY_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Geometry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEOMETRY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.geckoprojects.emf.example.model.basic.ContactType <em>Contact Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.geckoprojects.emf.example.model.basic.ContactType
	 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getContactType()
	 * @generated
	 */
	int CONTACT_TYPE = 16;

	/**
	 * The meta object id for the '{@link org.geckoprojects.emf.example.model.basic.ContactContextType <em>Contact Context Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.geckoprojects.emf.example.model.basic.ContactContextType
	 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getContactContextType()
	 * @generated
	 */
	int CONTACT_CONTEXT_TYPE = 17;

	/**
	 * The meta object id for the '{@link org.geckoprojects.emf.example.model.basic.GenderType <em>Gender Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.geckoprojects.emf.example.model.basic.GenderType
	 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getGenderType()
	 * @generated
	 */
	int GENDER_TYPE = 18;

	/**
	 * The meta object id for the '<em>NPE</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.NullPointerException
	 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getNPE()
	 * @generated
	 */
	int NPE = 19;

	/**
	 * The meta object id for the '<em>Coordinates</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getCoordinates()
	 * @generated
	 */
	int COORDINATES = 20;

	/**
	 * The meta object id for the '<em>Multi Dimension Coordinates</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getMultiDimensionCoordinates()
	 * @generated
	 */
	int MULTI_DIMENSION_COORDINATES = 21;


	/**
	 * Returns the meta object for class '{@link org.geckoprojects.emf.example.model.basic.Person <em>Person</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Person</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Person
	 * @generated
	 */
	EClass getPerson();

	/**
	 * Returns the meta object for the attribute '{@link org.geckoprojects.emf.example.model.basic.Person#getFirstName <em>First Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>First Name</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Person#getFirstName()
	 * @see #getPerson()
	 * @generated
	 */
	EAttribute getPerson_FirstName();

	/**
	 * Returns the meta object for the attribute '{@link org.geckoprojects.emf.example.model.basic.Person#getLastName <em>Last Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Last Name</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Person#getLastName()
	 * @see #getPerson()
	 * @generated
	 */
	EAttribute getPerson_LastName();

	/**
	 * Returns the meta object for the containment reference list '{@link org.geckoprojects.emf.example.model.basic.Person#getContact <em>Contact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Contact</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Person#getContact()
	 * @see #getPerson()
	 * @generated
	 */
	EReference getPerson_Contact();

	/**
	 * Returns the meta object for the reference '{@link org.geckoprojects.emf.example.model.basic.Person#getAddress <em>Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Address</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Person#getAddress()
	 * @see #getPerson()
	 * @generated
	 */
	EReference getPerson_Address();

	/**
	 * Returns the meta object for the attribute '{@link org.geckoprojects.emf.example.model.basic.Person#getGender <em>Gender</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Gender</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Person#getGender()
	 * @see #getPerson()
	 * @generated
	 */
	EAttribute getPerson_Gender();

	/**
	 * Returns the meta object for the containment reference list '{@link org.geckoprojects.emf.example.model.basic.Person#getTags <em>Tags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tags</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Person#getTags()
	 * @see #getPerson()
	 * @generated
	 */
	EReference getPerson_Tags();

	/**
	 * Returns the meta object for the attribute '{@link org.geckoprojects.emf.example.model.basic.Person#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Person#getId()
	 * @see #getPerson()
	 * @generated
	 */
	EAttribute getPerson_Id();

	/**
	 * Returns the meta object for the map '{@link org.geckoprojects.emf.example.model.basic.Person#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Properties</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Person#getProperties()
	 * @see #getPerson()
	 * @generated
	 */
	EReference getPerson_Properties();

	/**
	 * Returns the meta object for the attribute '{@link org.geckoprojects.emf.example.model.basic.Person#getBigInt <em>Big Int</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Big Int</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Person#getBigInt()
	 * @see #getPerson()
	 * @generated
	 */
	EAttribute getPerson_BigInt();

	/**
	 * Returns the meta object for the attribute list '{@link org.geckoprojects.emf.example.model.basic.Person#getBigDec <em>Big Dec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Big Dec</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Person#getBigDec()
	 * @see #getPerson()
	 * @generated
	 */
	EAttribute getPerson_BigDec();

	/**
	 * Returns the meta object for the attribute '{@link org.geckoprojects.emf.example.model.basic.Person#getImage <em>Image</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Image</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Person#getImage()
	 * @see #getPerson()
	 * @generated
	 */
	EAttribute getPerson_Image();

	/**
	 * Returns the meta object for the reference list '{@link org.geckoprojects.emf.example.model.basic.Person#getRelatives <em>Relatives</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Relatives</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Person#getRelatives()
	 * @see #getPerson()
	 * @generated
	 */
	EReference getPerson_Relatives();

	/**
	 * Returns the meta object for the reference '{@link org.geckoprojects.emf.example.model.basic.Person#getTransientAddress <em>Transient Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Transient Address</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Person#getTransientAddress()
	 * @see #getPerson()
	 * @generated
	 */
	EReference getPerson_TransientAddress();

	/**
	 * Returns the meta object for class '{@link org.geckoprojects.emf.example.model.basic.Address <em>Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Address</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Address
	 * @generated
	 */
	EClass getAddress();

	/**
	 * Returns the meta object for the attribute '{@link org.geckoprojects.emf.example.model.basic.Address#getStreet <em>Street</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Street</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Address#getStreet()
	 * @see #getAddress()
	 * @generated
	 */
	EAttribute getAddress_Street();

	/**
	 * Returns the meta object for the attribute '{@link org.geckoprojects.emf.example.model.basic.Address#getCity <em>City</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>City</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Address#getCity()
	 * @see #getAddress()
	 * @generated
	 */
	EAttribute getAddress_City();

	/**
	 * Returns the meta object for the attribute '{@link org.geckoprojects.emf.example.model.basic.Address#getZip <em>Zip</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Zip</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Address#getZip()
	 * @see #getAddress()
	 * @generated
	 */
	EAttribute getAddress_Zip();

	/**
	 * Returns the meta object for the attribute '{@link org.geckoprojects.emf.example.model.basic.Address#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Address#getId()
	 * @see #getAddress()
	 * @generated
	 */
	EAttribute getAddress_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.geckoprojects.emf.example.model.basic.Address#getNpe <em>Npe</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Npe</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Address#getNpe()
	 * @see #getAddress()
	 * @generated
	 */
	EAttribute getAddress_Npe();

	/**
	 * Returns the meta object for class '{@link org.geckoprojects.emf.example.model.basic.Contact <em>Contact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Contact</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Contact
	 * @generated
	 */
	EClass getContact();

	/**
	 * Returns the meta object for the attribute '{@link org.geckoprojects.emf.example.model.basic.Contact#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Contact#getType()
	 * @see #getContact()
	 * @generated
	 */
	EAttribute getContact_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.geckoprojects.emf.example.model.basic.Contact#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Contact#getValue()
	 * @see #getContact()
	 * @generated
	 */
	EAttribute getContact_Value();

	/**
	 * Returns the meta object for the attribute '{@link org.geckoprojects.emf.example.model.basic.Contact#getContext <em>Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Context</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Contact#getContext()
	 * @see #getContact()
	 * @generated
	 */
	EAttribute getContact_Context();

	/**
	 * Returns the meta object for class '{@link org.geckoprojects.emf.example.model.basic.Family <em>Family</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Family</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Family
	 * @generated
	 */
	EClass getFamily();

	/**
	 * Returns the meta object for the reference '{@link org.geckoprojects.emf.example.model.basic.Family#getFather <em>Father</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Father</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Family#getFather()
	 * @see #getFamily()
	 * @generated
	 */
	EReference getFamily_Father();

	/**
	 * Returns the meta object for the reference '{@link org.geckoprojects.emf.example.model.basic.Family#getMother <em>Mother</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Mother</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Family#getMother()
	 * @see #getFamily()
	 * @generated
	 */
	EReference getFamily_Mother();

	/**
	 * Returns the meta object for the reference list '{@link org.geckoprojects.emf.example.model.basic.Family#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Children</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Family#getChildren()
	 * @see #getFamily()
	 * @generated
	 */
	EReference getFamily_Children();

	/**
	 * Returns the meta object for the attribute '{@link org.geckoprojects.emf.example.model.basic.Family#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Family#getId()
	 * @see #getFamily()
	 * @generated
	 */
	EAttribute getFamily_Id();

	/**
	 * Returns the meta object for class '{@link org.geckoprojects.emf.example.model.basic.BusinessContact <em>Business Contact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Business Contact</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.BusinessContact
	 * @generated
	 */
	EClass getBusinessContact();

	/**
	 * Returns the meta object for the attribute '{@link org.geckoprojects.emf.example.model.basic.BusinessContact#getCompanyName <em>Company Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Company Name</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.BusinessContact#getCompanyName()
	 * @see #getBusinessContact()
	 * @generated
	 */
	EAttribute getBusinessContact_CompanyName();

	/**
	 * Returns the meta object for class '{@link org.geckoprojects.emf.example.model.basic.Tag <em>Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Tag
	 * @generated
	 */
	EClass getTag();

	/**
	 * Returns the meta object for the attribute '{@link org.geckoprojects.emf.example.model.basic.Tag#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Tag#getName()
	 * @see #getTag()
	 * @generated
	 */
	EAttribute getTag_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.geckoprojects.emf.example.model.basic.Tag#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Tag#getValue()
	 * @see #getTag()
	 * @generated
	 */
	EAttribute getTag_Value();

	/**
	 * Returns the meta object for the containment reference '{@link org.geckoprojects.emf.example.model.basic.Tag#getTag <em>Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Tag</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Tag#getTag()
	 * @see #getTag()
	 * @generated
	 */
	EReference getTag_Tag();

	/**
	 * Returns the meta object for the containment reference list '{@link org.geckoprojects.emf.example.model.basic.Tag#getTags <em>Tags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tags</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Tag#getTags()
	 * @see #getTag()
	 * @generated
	 */
	EReference getTag_Tags();

	/**
	 * Returns the meta object for the attribute '{@link org.geckoprojects.emf.example.model.basic.Tag#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Tag#getDescription()
	 * @see #getTag()
	 * @generated
	 */
	EAttribute getTag_Description();

	/**
	 * Returns the meta object for class '{@link org.geckoprojects.emf.example.model.basic.BusinessPerson <em>Business Person</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Business Person</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.BusinessPerson
	 * @generated
	 */
	EClass getBusinessPerson();

	/**
	 * Returns the meta object for the attribute '{@link org.geckoprojects.emf.example.model.basic.BusinessPerson#getCompanyIdCardNumber <em>Company Id Card Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Company Id Card Number</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.BusinessPerson#getCompanyIdCardNumber()
	 * @see #getBusinessPerson()
	 * @generated
	 */
	EAttribute getBusinessPerson_CompanyIdCardNumber();

	/**
	 * Returns the meta object for the containment reference list '{@link org.geckoprojects.emf.example.model.basic.BusinessPerson#getEmployeeInfo <em>Employee Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Employee Info</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.BusinessPerson#getEmployeeInfo()
	 * @see #getBusinessPerson()
	 * @generated
	 */
	EReference getBusinessPerson_EmployeeInfo();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>String String Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String String Map</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
	 *        valueDataType="org.eclipse.emf.ecore.EString"
	 * @generated
	 */
	EClass getStringStringMap();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringStringMap()
	 * @generated
	 */
	EAttribute getStringStringMap_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringStringMap()
	 * @generated
	 */
	EAttribute getStringStringMap_Value();

	/**
	 * Returns the meta object for class '{@link org.geckoprojects.emf.example.model.basic.EmployeeInfo <em>Employee Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Employee Info</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.EmployeeInfo
	 * @generated
	 */
	EClass getEmployeeInfo();

	/**
	 * Returns the meta object for the attribute '{@link org.geckoprojects.emf.example.model.basic.EmployeeInfo#getPosition <em>Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Position</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.EmployeeInfo#getPosition()
	 * @see #getEmployeeInfo()
	 * @generated
	 */
	EAttribute getEmployeeInfo_Position();

	/**
	 * Returns the meta object for class '{@link org.geckoprojects.emf.example.model.basic.Widget <em>Widget</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Widget</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Widget
	 * @generated
	 */
	EClass getWidget();

	/**
	 * Returns the meta object for the containment reference '{@link org.geckoprojects.emf.example.model.basic.Widget#getContent <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Content</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Widget#getContent()
	 * @see #getWidget()
	 * @generated
	 */
	EReference getWidget_Content();

	/**
	 * Returns the meta object for the attribute '{@link org.geckoprojects.emf.example.model.basic.Widget#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Widget#getId()
	 * @see #getWidget()
	 * @generated
	 */
	EAttribute getWidget_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.geckoprojects.emf.example.model.basic.Widget#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Widget#getName()
	 * @see #getWidget()
	 * @generated
	 */
	EAttribute getWidget_Name();

	/**
	 * Returns the meta object for class '{@link org.geckoprojects.emf.example.model.basic.Textwidget <em>Textwidget</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Textwidget</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Textwidget
	 * @generated
	 */
	EClass getTextwidget();

	/**
	 * Returns the meta object for class '{@link org.geckoprojects.emf.example.model.basic.Content <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Content</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Content
	 * @generated
	 */
	EClass getContent();

	/**
	 * Returns the meta object for the attribute '{@link org.geckoprojects.emf.example.model.basic.Content#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Content#getName()
	 * @see #getContent()
	 * @generated
	 */
	EAttribute getContent_Name();

	/**
	 * Returns the meta object for class '{@link org.geckoprojects.emf.example.model.basic.HLWidget <em>HL Widget</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>HL Widget</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.HLWidget
	 * @generated
	 */
	EClass getHLWidget();

	/**
	 * Returns the meta object for the containment reference list '{@link org.geckoprojects.emf.example.model.basic.HLWidget#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.HLWidget#getChildren()
	 * @see #getHLWidget()
	 * @generated
	 */
	EReference getHLWidget_Children();

	/**
	 * Returns the meta object for class '{@link org.geckoprojects.emf.example.model.basic.PersonContact <em>Person Contact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Person Contact</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.PersonContact
	 * @generated
	 */
	EClass getPersonContact();

	/**
	 * Returns the meta object for the reference '{@link org.geckoprojects.emf.example.model.basic.PersonContact#getContactPerson <em>Contact Person</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Contact Person</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.PersonContact#getContactPerson()
	 * @see #getPersonContact()
	 * @generated
	 */
	EReference getPersonContact_ContactPerson();

	/**
	 * Returns the meta object for class '{@link org.geckoprojects.emf.example.model.basic.PersonObject <em>Person Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Person Object</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.PersonObject
	 * @generated
	 */
	EClass getPersonObject();

	/**
	 * Returns the meta object for the reference '{@link org.geckoprojects.emf.example.model.basic.PersonObject#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.PersonObject#getType()
	 * @see #getPersonObject()
	 * @generated
	 */
	EReference getPersonObject_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.geckoprojects.emf.example.model.basic.PersonObject#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.PersonObject#getId()
	 * @see #getPersonObject()
	 * @generated
	 */
	EAttribute getPersonObject_Id();

	/**
	 * Returns the meta object for class '{@link org.geckoprojects.emf.example.model.basic.Geometry <em>Geometry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Geometry</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Geometry
	 * @generated
	 */
	EClass getGeometry();

	/**
	 * Returns the meta object for the attribute list '{@link org.geckoprojects.emf.example.model.basic.Geometry#getCoordinates <em>Coordinates</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Coordinates</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Geometry#getCoordinates()
	 * @see #getGeometry()
	 * @generated
	 */
	EAttribute getGeometry_Coordinates();

	/**
	 * Returns the meta object for the attribute '{@link org.geckoprojects.emf.example.model.basic.Geometry#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Geometry#getId()
	 * @see #getGeometry()
	 * @generated
	 */
	EAttribute getGeometry_Id();

	/**
	 * Returns the meta object for the attribute list '{@link org.geckoprojects.emf.example.model.basic.Geometry#getMultiDimensionalCoordinates <em>Multi Dimensional Coordinates</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Multi Dimensional Coordinates</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.Geometry#getMultiDimensionalCoordinates()
	 * @see #getGeometry()
	 * @generated
	 */
	EAttribute getGeometry_MultiDimensionalCoordinates();

	/**
	 * Returns the meta object for enum '{@link org.geckoprojects.emf.example.model.basic.ContactType <em>Contact Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Contact Type</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.ContactType
	 * @generated
	 */
	EEnum getContactType();

	/**
	 * Returns the meta object for enum '{@link org.geckoprojects.emf.example.model.basic.ContactContextType <em>Contact Context Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Contact Context Type</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.ContactContextType
	 * @generated
	 */
	EEnum getContactContextType();

	/**
	 * Returns the meta object for enum '{@link org.geckoprojects.emf.example.model.basic.GenderType <em>Gender Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Gender Type</em>'.
	 * @see org.geckoprojects.emf.example.model.basic.GenderType
	 * @generated
	 */
	EEnum getGenderType();

	/**
	 * Returns the meta object for data type '{@link java.lang.NullPointerException <em>NPE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>NPE</em>'.
	 * @see java.lang.NullPointerException
	 * @model instanceClass="java.lang.NullPointerException"
	 * @generated
	 */
	EDataType getNPE();

	/**
	 * Returns the meta object for data type '<em>Coordinates</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Coordinates</em>'.
	 * @model instanceClass="java.lang.Double[]"
	 * @generated
	 */
	EDataType getCoordinates();

	/**
	 * Returns the meta object for data type '<em>Multi Dimension Coordinates</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Multi Dimension Coordinates</em>'.
	 * @model instanceClass="java.lang.Double[][]"
	 * @generated
	 */
	EDataType getMultiDimensionCoordinates();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	BasicFactory getBasicFactory();

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
		 * The meta object literal for the '{@link org.geckoprojects.emf.example.model.basic.impl.PersonImpl <em>Person</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.geckoprojects.emf.example.model.basic.impl.PersonImpl
		 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getPerson()
		 * @generated
		 */
		EClass PERSON = eINSTANCE.getPerson();

		/**
		 * The meta object literal for the '<em><b>First Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERSON__FIRST_NAME = eINSTANCE.getPerson_FirstName();

		/**
		 * The meta object literal for the '<em><b>Last Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERSON__LAST_NAME = eINSTANCE.getPerson_LastName();

		/**
		 * The meta object literal for the '<em><b>Contact</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERSON__CONTACT = eINSTANCE.getPerson_Contact();

		/**
		 * The meta object literal for the '<em><b>Address</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERSON__ADDRESS = eINSTANCE.getPerson_Address();

		/**
		 * The meta object literal for the '<em><b>Gender</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERSON__GENDER = eINSTANCE.getPerson_Gender();

		/**
		 * The meta object literal for the '<em><b>Tags</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERSON__TAGS = eINSTANCE.getPerson_Tags();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERSON__ID = eINSTANCE.getPerson_Id();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERSON__PROPERTIES = eINSTANCE.getPerson_Properties();

		/**
		 * The meta object literal for the '<em><b>Big Int</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERSON__BIG_INT = eINSTANCE.getPerson_BigInt();

		/**
		 * The meta object literal for the '<em><b>Big Dec</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERSON__BIG_DEC = eINSTANCE.getPerson_BigDec();

		/**
		 * The meta object literal for the '<em><b>Image</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERSON__IMAGE = eINSTANCE.getPerson_Image();

		/**
		 * The meta object literal for the '<em><b>Relatives</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERSON__RELATIVES = eINSTANCE.getPerson_Relatives();

		/**
		 * The meta object literal for the '<em><b>Transient Address</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERSON__TRANSIENT_ADDRESS = eINSTANCE.getPerson_TransientAddress();

		/**
		 * The meta object literal for the '{@link org.geckoprojects.emf.example.model.basic.impl.AddressImpl <em>Address</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.geckoprojects.emf.example.model.basic.impl.AddressImpl
		 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getAddress()
		 * @generated
		 */
		EClass ADDRESS = eINSTANCE.getAddress();

		/**
		 * The meta object literal for the '<em><b>Street</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADDRESS__STREET = eINSTANCE.getAddress_Street();

		/**
		 * The meta object literal for the '<em><b>City</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADDRESS__CITY = eINSTANCE.getAddress_City();

		/**
		 * The meta object literal for the '<em><b>Zip</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADDRESS__ZIP = eINSTANCE.getAddress_Zip();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADDRESS__ID = eINSTANCE.getAddress_Id();

		/**
		 * The meta object literal for the '<em><b>Npe</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADDRESS__NPE = eINSTANCE.getAddress_Npe();

		/**
		 * The meta object literal for the '{@link org.geckoprojects.emf.example.model.basic.impl.ContactImpl <em>Contact</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.geckoprojects.emf.example.model.basic.impl.ContactImpl
		 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getContact()
		 * @generated
		 */
		EClass CONTACT = eINSTANCE.getContact();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTACT__TYPE = eINSTANCE.getContact_Type();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTACT__VALUE = eINSTANCE.getContact_Value();

		/**
		 * The meta object literal for the '<em><b>Context</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTACT__CONTEXT = eINSTANCE.getContact_Context();

		/**
		 * The meta object literal for the '{@link org.geckoprojects.emf.example.model.basic.impl.FamilyImpl <em>Family</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.geckoprojects.emf.example.model.basic.impl.FamilyImpl
		 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getFamily()
		 * @generated
		 */
		EClass FAMILY = eINSTANCE.getFamily();

		/**
		 * The meta object literal for the '<em><b>Father</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FAMILY__FATHER = eINSTANCE.getFamily_Father();

		/**
		 * The meta object literal for the '<em><b>Mother</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FAMILY__MOTHER = eINSTANCE.getFamily_Mother();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FAMILY__CHILDREN = eINSTANCE.getFamily_Children();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FAMILY__ID = eINSTANCE.getFamily_Id();

		/**
		 * The meta object literal for the '{@link org.geckoprojects.emf.example.model.basic.impl.BusinessContactImpl <em>Business Contact</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.geckoprojects.emf.example.model.basic.impl.BusinessContactImpl
		 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getBusinessContact()
		 * @generated
		 */
		EClass BUSINESS_CONTACT = eINSTANCE.getBusinessContact();

		/**
		 * The meta object literal for the '<em><b>Company Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUSINESS_CONTACT__COMPANY_NAME = eINSTANCE.getBusinessContact_CompanyName();

		/**
		 * The meta object literal for the '{@link org.geckoprojects.emf.example.model.basic.impl.TagImpl <em>Tag</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.geckoprojects.emf.example.model.basic.impl.TagImpl
		 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getTag()
		 * @generated
		 */
		EClass TAG = eINSTANCE.getTag();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG__NAME = eINSTANCE.getTag_Name();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG__VALUE = eINSTANCE.getTag_Value();

		/**
		 * The meta object literal for the '<em><b>Tag</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAG__TAG = eINSTANCE.getTag_Tag();

		/**
		 * The meta object literal for the '<em><b>Tags</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAG__TAGS = eINSTANCE.getTag_Tags();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG__DESCRIPTION = eINSTANCE.getTag_Description();

		/**
		 * The meta object literal for the '{@link org.geckoprojects.emf.example.model.basic.impl.BusinessPersonImpl <em>Business Person</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.geckoprojects.emf.example.model.basic.impl.BusinessPersonImpl
		 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getBusinessPerson()
		 * @generated
		 */
		EClass BUSINESS_PERSON = eINSTANCE.getBusinessPerson();

		/**
		 * The meta object literal for the '<em><b>Company Id Card Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUSINESS_PERSON__COMPANY_ID_CARD_NUMBER = eINSTANCE.getBusinessPerson_CompanyIdCardNumber();

		/**
		 * The meta object literal for the '<em><b>Employee Info</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BUSINESS_PERSON__EMPLOYEE_INFO = eINSTANCE.getBusinessPerson_EmployeeInfo();

		/**
		 * The meta object literal for the '{@link org.geckoprojects.emf.example.model.basic.impl.StringStringMapImpl <em>String String Map</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.geckoprojects.emf.example.model.basic.impl.StringStringMapImpl
		 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getStringStringMap()
		 * @generated
		 */
		EClass STRING_STRING_MAP = eINSTANCE.getStringStringMap();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_STRING_MAP__KEY = eINSTANCE.getStringStringMap_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_STRING_MAP__VALUE = eINSTANCE.getStringStringMap_Value();

		/**
		 * The meta object literal for the '{@link org.geckoprojects.emf.example.model.basic.impl.EmployeeInfoImpl <em>Employee Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.geckoprojects.emf.example.model.basic.impl.EmployeeInfoImpl
		 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getEmployeeInfo()
		 * @generated
		 */
		EClass EMPLOYEE_INFO = eINSTANCE.getEmployeeInfo();

		/**
		 * The meta object literal for the '<em><b>Position</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EMPLOYEE_INFO__POSITION = eINSTANCE.getEmployeeInfo_Position();

		/**
		 * The meta object literal for the '{@link org.geckoprojects.emf.example.model.basic.impl.WidgetImpl <em>Widget</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.geckoprojects.emf.example.model.basic.impl.WidgetImpl
		 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getWidget()
		 * @generated
		 */
		EClass WIDGET = eINSTANCE.getWidget();

		/**
		 * The meta object literal for the '<em><b>Content</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WIDGET__CONTENT = eINSTANCE.getWidget_Content();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WIDGET__ID = eINSTANCE.getWidget_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WIDGET__NAME = eINSTANCE.getWidget_Name();

		/**
		 * The meta object literal for the '{@link org.geckoprojects.emf.example.model.basic.impl.TextwidgetImpl <em>Textwidget</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.geckoprojects.emf.example.model.basic.impl.TextwidgetImpl
		 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getTextwidget()
		 * @generated
		 */
		EClass TEXTWIDGET = eINSTANCE.getTextwidget();

		/**
		 * The meta object literal for the '{@link org.geckoprojects.emf.example.model.basic.impl.ContentImpl <em>Content</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.geckoprojects.emf.example.model.basic.impl.ContentImpl
		 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getContent()
		 * @generated
		 */
		EClass CONTENT = eINSTANCE.getContent();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTENT__NAME = eINSTANCE.getContent_Name();

		/**
		 * The meta object literal for the '{@link org.geckoprojects.emf.example.model.basic.impl.HLWidgetImpl <em>HL Widget</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.geckoprojects.emf.example.model.basic.impl.HLWidgetImpl
		 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getHLWidget()
		 * @generated
		 */
		EClass HL_WIDGET = eINSTANCE.getHLWidget();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HL_WIDGET__CHILDREN = eINSTANCE.getHLWidget_Children();

		/**
		 * The meta object literal for the '{@link org.geckoprojects.emf.example.model.basic.impl.PersonContactImpl <em>Person Contact</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.geckoprojects.emf.example.model.basic.impl.PersonContactImpl
		 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getPersonContact()
		 * @generated
		 */
		EClass PERSON_CONTACT = eINSTANCE.getPersonContact();

		/**
		 * The meta object literal for the '<em><b>Contact Person</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERSON_CONTACT__CONTACT_PERSON = eINSTANCE.getPersonContact_ContactPerson();

		/**
		 * The meta object literal for the '{@link org.geckoprojects.emf.example.model.basic.impl.PersonObjectImpl <em>Person Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.geckoprojects.emf.example.model.basic.impl.PersonObjectImpl
		 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getPersonObject()
		 * @generated
		 */
		EClass PERSON_OBJECT = eINSTANCE.getPersonObject();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERSON_OBJECT__TYPE = eINSTANCE.getPersonObject_Type();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERSON_OBJECT__ID = eINSTANCE.getPersonObject_Id();

		/**
		 * The meta object literal for the '{@link org.geckoprojects.emf.example.model.basic.impl.GeometryImpl <em>Geometry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.geckoprojects.emf.example.model.basic.impl.GeometryImpl
		 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getGeometry()
		 * @generated
		 */
		EClass GEOMETRY = eINSTANCE.getGeometry();

		/**
		 * The meta object literal for the '<em><b>Coordinates</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GEOMETRY__COORDINATES = eINSTANCE.getGeometry_Coordinates();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GEOMETRY__ID = eINSTANCE.getGeometry_Id();

		/**
		 * The meta object literal for the '<em><b>Multi Dimensional Coordinates</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GEOMETRY__MULTI_DIMENSIONAL_COORDINATES = eINSTANCE.getGeometry_MultiDimensionalCoordinates();

		/**
		 * The meta object literal for the '{@link org.geckoprojects.emf.example.model.basic.ContactType <em>Contact Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.geckoprojects.emf.example.model.basic.ContactType
		 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getContactType()
		 * @generated
		 */
		EEnum CONTACT_TYPE = eINSTANCE.getContactType();

		/**
		 * The meta object literal for the '{@link org.geckoprojects.emf.example.model.basic.ContactContextType <em>Contact Context Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.geckoprojects.emf.example.model.basic.ContactContextType
		 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getContactContextType()
		 * @generated
		 */
		EEnum CONTACT_CONTEXT_TYPE = eINSTANCE.getContactContextType();

		/**
		 * The meta object literal for the '{@link org.geckoprojects.emf.example.model.basic.GenderType <em>Gender Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.geckoprojects.emf.example.model.basic.GenderType
		 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getGenderType()
		 * @generated
		 */
		EEnum GENDER_TYPE = eINSTANCE.getGenderType();

		/**
		 * The meta object literal for the '<em>NPE</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.NullPointerException
		 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getNPE()
		 * @generated
		 */
		EDataType NPE = eINSTANCE.getNPE();

		/**
		 * The meta object literal for the '<em>Coordinates</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getCoordinates()
		 * @generated
		 */
		EDataType COORDINATES = eINSTANCE.getCoordinates();

		/**
		 * The meta object literal for the '<em>Multi Dimension Coordinates</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.geckoprojects.emf.example.model.basic.impl.BasicPackageImpl#getMultiDimensionCoordinates()
		 * @generated
		 */
		EDataType MULTI_DIMENSION_COORDINATES = eINSTANCE.getMultiDimensionCoordinates();

	}

} //BasicPackage
