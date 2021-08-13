/**
 */
package org.geckoprojects.emf.example.model.manual;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

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
 * @see org.geckoprojects.emf.example.model.manual.ManualFactory
 * @model kind="package"
 * @generated
 */
public interface ManualPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "manual";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://geckoprojects.org/example/model/manual/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "manual";

	/**
	 * The package content type ID.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eCONTENT_TYPE = "manual#1.0";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ManualPackage eINSTANCE = org.geckoprojects.emf.example.model.manual.impl.ManualPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.geckoprojects.emf.example.model.manual.impl.FooImpl <em>Foo</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.geckoprojects.emf.example.model.manual.impl.FooImpl
	 * @see org.geckoprojects.emf.example.model.manual.impl.ManualPackageImpl#getFoo()
	 * @generated
	 */
	int FOO = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOO__VALUE = 0;

	/**
	 * The number of structural features of the '<em>Foo</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOO_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Foo</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOO_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link org.geckoprojects.emf.example.model.manual.Foo <em>Foo</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Foo</em>'.
	 * @see org.geckoprojects.emf.example.model.manual.Foo
	 * @generated
	 */
	EClass getFoo();

	/**
	 * Returns the meta object for the attribute '{@link org.geckoprojects.emf.example.model.manual.Foo#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.geckoprojects.emf.example.model.manual.Foo#getValue()
	 * @see #getFoo()
	 * @generated
	 */
	EAttribute getFoo_Value();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ManualFactory getManualFactory();

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
		 * The meta object literal for the '{@link org.geckoprojects.emf.example.model.manual.impl.FooImpl <em>Foo</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.geckoprojects.emf.example.model.manual.impl.FooImpl
		 * @see org.geckoprojects.emf.example.model.manual.impl.ManualPackageImpl#getFoo()
		 * @generated
		 */
		EClass FOO = eINSTANCE.getFoo();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FOO__VALUE = eINSTANCE.getFoo_Value();

	}

} //ManualPackage
