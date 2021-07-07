/**
 */
package org.geckoprojects.emf.pushstream;

import java.util.concurrent.BlockingQueue;

import org.eclipse.emf.ecore.EObject;
import org.osgi.util.pushstream.PushEvent;
import org.osgi.util.pushstream.PushStream;
import org.osgi.util.pushstream.PushStreamBuilder;
import org.osgi.util.pushstream.SimplePushEventSource;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EPush Stream Provider</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.geckoprojects.emf.pushstream.PushStreamPackage#getEPushStreamProvider()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface EPushStreamProvider extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Creates a PushStream from the internal eventSource feature
	 * <!-- end-model-doc -->
	 * @model dataType="org.geckoprojects.emf.pushstream.EPushStream" required="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='PushStreamProvider psp = getProvider();\nif (psp == null) {\n\tthrow new &lt;%java.lang.IllegalArgumentException%&gt;(\"PushStreamProvider must not be null to build a PushStream\");\n}\n&lt;%org.osgi.util.pushstream.PushEventSource%&gt;&lt;EObject&gt; es = getEventSource();\nif (es == null) {\n\tthrow new &lt;%java.lang.IllegalArgumentException%&gt;(\"SimpleEventSource must not be null to build a PushStream\");\n}\nreturn psp.buildStream(es).build();\n'"
	 * @generated
	 */
	PushStream<EObject> createPushStream();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Creates a new SimplePushEventSource from the given PushStreamProvider
	 * <!-- end-model-doc -->
	 * @model dataType="org.geckoprojects.emf.pushstream.ESimplePushEventSource" required="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='PushStreamProvider psp = getProvider();\nif (psp == null) {\n\tthrow new &lt;%java.lang.IllegalArgumentException%&gt;(\"PushStreamProvider must not be null to create a SimplePushEventSource\");\n}\nreturn psp.buildSimpleEventSource(EObject.class).build();\n'"
	 * @generated
	 */
	SimplePushEventSource<EObject> createSimplePushEventSource();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Creates a PushStream from the internal eventSource feature
	 * <!-- end-model-doc -->
	 * @model dataType="org.geckoprojects.emf.pushstream.EPushStream" required="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='PushStreamProvider psp = getProvider();\nif (psp == null) {\n\tthrow new &lt;%java.lang.IllegalArgumentException%&gt;(\"PushStreamProvider must not be null to build a PushStream\");\n}\n&lt;%org.osgi.util.pushstream.PushEventSource%&gt;&lt;EObject&gt; es = getEventSource();\nif (es == null) {\n\tthrow new &lt;%java.lang.IllegalArgumentException%&gt;(\"SimpleEventSource must not be null to build a PushStream\");\n}\nreturn psp.buildStream(es).unbuffered().build();\n'"
	 * @generated
	 */
	PushStream<EObject> createPushStreamUnbuffered();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Creates a PushStreamBuilder to customize the settings
	 * <!-- end-model-doc -->
	 * @model dataType="org.geckoprojects.emf.pushstream.PushStreamBuilder"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='PushStreamProvider psp = getProvider();\r\nif (psp == null) {\r\n\tthrow new &lt;%java.lang.IllegalArgumentException%&gt;(\"PushStreamProvider must not be null to build a PushStream\");\r\n}\r\n&lt;%org.osgi.util.pushstream.PushEventSource%&gt;&lt;EObject&gt; es = getEventSource();\r\nif (es == null) {\r\n\tthrow new &lt;%java.lang.IllegalArgumentException%&gt;(\"SimpleEventSource must not be null to build a PushStream\");\r\n}\r\nreturn psp.buildStream(es);\r\n'"
	 * @generated
	 */
	PushStreamBuilder<EObject, BlockingQueue<PushEvent<? extends EObject>>> createPushStreamBuilder();

} // EPushStreamProvider
