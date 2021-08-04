/**
 */
package org.geckoprojects.emf.pushstream;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.geckoprojects.emf.pushstream.PushStreamPackage
 * @generated
 */
public interface PushStreamFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PushStreamFactory eINSTANCE = org.geckoprojects.emf.pushstream.impl.PushStreamFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Simple Push Stream Provider</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Simple Push Stream Provider</em>'.
	 * @generated
	 */
	SimplePushStreamProvider createSimplePushStreamProvider();

	/**
	 * Returns a new object of class '<em>Custom Push Stream Provider</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Custom Push Stream Provider</em>'.
	 * @generated
	 */
	CustomPushStreamProvider createCustomPushStreamProvider();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	PushStreamPackage getPushStreamPackage();

} //PushStreamFactory
