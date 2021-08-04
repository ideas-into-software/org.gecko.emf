/**
 */
package org.geckoprojects.emf.pushstream;

import org.eclipse.emf.ecore.EObject;
import org.osgi.util.pushstream.PushEventSource;
import org.osgi.util.pushstream.PushStreamProvider;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Custom Push Stream Provider</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.geckoprojects.emf.pushstream.CustomPushStreamProvider#getEventSource <em>Event Source</em>}</li>
 *   <li>{@link org.geckoprojects.emf.pushstream.CustomPushStreamProvider#getProvider <em>Provider</em>}</li>
 * </ul>
 *
 * @see org.geckoprojects.emf.pushstream.PushStreamPackage#getCustomPushStreamProvider()
 * @model
 * @generated
 */
public interface CustomPushStreamProvider extends EPushStreamProvider {
	/**
	 * Returns the value of the '<em><b>Event Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event Source</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event Source</em>' attribute.
	 * @see #setEventSource(PushEventSource)
	 * @see org.geckoprojects.emf.pushstream.PushStreamPackage#getCustomPushStreamProvider_EventSource()
	 * @model dataType="org.geckoprojects.emf.pushstream.EPushEventSource" required="true" transient="true"
	 * @generated
	 */
	PushEventSource<EObject> getEventSource();

	/**
	 * Sets the value of the '{@link org.geckoprojects.emf.pushstream.CustomPushStreamProvider#getEventSource <em>Event Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event Source</em>' attribute.
	 * @see #getEventSource()
	 * @generated
	 */
	void setEventSource(PushEventSource<EObject> value);

	/**
	 * Returns the value of the '<em><b>Provider</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Provider</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Provider</em>' attribute.
	 * @see #setProvider(PushStreamProvider)
	 * @see org.geckoprojects.emf.pushstream.PushStreamPackage#getCustomPushStreamProvider_Provider()
	 * @model dataType="org.geckoprojects.emf.pushstream.PushStreamProvider" required="true"
	 * @generated
	 */
	PushStreamProvider getProvider();

	/**
	 * Sets the value of the '{@link org.geckoprojects.emf.pushstream.CustomPushStreamProvider#getProvider <em>Provider</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Provider</em>' attribute.
	 * @see #getProvider()
	 * @generated
	 */
	void setProvider(PushStreamProvider value);

} // CustomPushStreamProvider
