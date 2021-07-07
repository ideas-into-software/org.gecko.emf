/**
 */
package org.geckoprojects.emf.example.model.extended.model.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.geckoprojects.emf.example.model.basic.model.Address;
import org.geckoprojects.emf.example.model.basic.model.EmployeeInfo;
import org.geckoprojects.emf.example.model.basic.model.Person;
import org.geckoprojects.emf.example.model.extended.model.ExtendedAddress;
import org.geckoprojects.emf.example.model.extended.model.ExtendedPerson;
import org.geckoprojects.emf.example.model.extended.model.ExtendedPrefixPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.geckoprojects.emf.example.model.extended.model.ExtendedPrefixPackage
 * @generated
 */
public class ExtendedPrefixAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ExtendedPrefixPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExtendedPrefixAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ExtendedPrefixPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExtendedPrefixSwitch<Adapter> modelSwitch =
		new ExtendedPrefixSwitch<Adapter>() {
			@Override
			public Adapter caseExtendedAddress(ExtendedAddress object) {
				return createExtendedAddressAdapter();
			}
			@Override
			public Adapter caseExtendedPerson(ExtendedPerson object) {
				return createExtendedPersonAdapter();
			}
			@Override
			public Adapter caseAddress(Address object) {
				return createAddressAdapter();
			}
			@Override
			public Adapter caseEmployeeInfo(EmployeeInfo object) {
				return createEmployeeInfoAdapter();
			}
			@Override
			public Adapter casePerson(Person object) {
				return createPersonAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.geckoprojects.emf.example.model.extended.model.ExtendedAddress <em>Extended Address</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.geckoprojects.emf.example.model.extended.model.ExtendedAddress
	 * @generated
	 */
	public Adapter createExtendedAddressAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.geckoprojects.emf.example.model.extended.model.ExtendedPerson <em>Extended Person</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.geckoprojects.emf.example.model.extended.model.ExtendedPerson
	 * @generated
	 */
	public Adapter createExtendedPersonAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.geckoprojects.emf.example.model.basic.model.Address <em>Address</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.geckoprojects.emf.example.model.basic.model.Address
	 * @generated
	 */
	public Adapter createAddressAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.geckoprojects.emf.example.model.basic.model.EmployeeInfo <em>Employee Info</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.geckoprojects.emf.example.model.basic.model.EmployeeInfo
	 * @generated
	 */
	public Adapter createEmployeeInfoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.geckoprojects.emf.example.model.basic.model.Person <em>Person</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.geckoprojects.emf.example.model.basic.model.Person
	 * @generated
	 */
	public Adapter createPersonAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //ExtendedPrefixAdapterFactory
