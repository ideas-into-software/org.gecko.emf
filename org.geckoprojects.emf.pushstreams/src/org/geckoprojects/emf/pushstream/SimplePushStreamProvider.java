/**
 */
package org.geckoprojects.emf.pushstream;

import org.eclipse.emf.ecore.EObject;
import org.osgi.util.pushstream.PushStreamProvider;
import org.osgi.util.pushstream.SimplePushEventSource;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Simple Push Stream Provider</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.geckoprojects.emf.pushstream.SimplePushStreamProvider#getProvider <em>Provider</em>}</li>
 *   <li>{@link org.geckoprojects.emf.pushstream.SimplePushStreamProvider#getEventSource <em>Event Source</em>}</li>
 * </ul>
 *
 * @see org.geckoprojects.emf.pushstream.PushStreamPackage#getSimplePushStreamProvider()
 * @model features="internalSource" 
 *        internalSourceDataType="org.gecko.emf.pushstream.ESimplePushEventSource" internalSourceTransient="true" internalSourceChangeable="false" internalSourceDerived="true" internalSourceSuppressedGetVisibility="true"
 * @generated
 */
public interface SimplePushStreamProvider extends EPushStreamProvider {
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
	 * @see org.geckoprojects.emf.pushstream.PushStreamPackage#getSimplePushStreamProvider_Provider()
	 * @model dataType="org.gecko.emf.pushstream.PushStreamProvider" required="true"
	 * @generated
	 */
	PushStreamProvider getProvider();

	/**
	 * Sets the value of the '{@link org.geckoprojects.emf.pushstream.SimplePushStreamProvider#getProvider <em>Provider</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Provider</em>' attribute.
	 * @see #getProvider()
	 * @generated
	 */
	void setProvider(PushStreamProvider value);

	/**
	 * Returns the value of the '<em><b>Event Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event Source</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * We generate the code for the getter and lazy intialize the internalSource feature
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Event Source</em>' attribute.
	 * @see org.geckoprojects.emf.pushstream.PushStreamPackage#getSimplePushStreamProvider_EventSource()
	 * @model dataType="org.gecko.emf.pushstream.ESimplePushEventSource" required="true" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel get='if (internalSource == null) {\r\n\tinternalSource = createSimplePushEventSource();\r\n}\r\nreturn internalSource;'"
	 * @generated
	 */
	SimplePushEventSource<EObject> getEventSource();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Publishes an EObject into the internal eventSource
	 * <!-- end-model-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='SimplePushEventSource&lt;EObject&gt; es = getEventSource();\nif (eObject != null &amp;&amp; es != null) {\n\tes.publish(eObject);\n};'"
	 * @generated
	 */
	void publishEObject(EObject eObject);

} // SimplePushStreamProvider
