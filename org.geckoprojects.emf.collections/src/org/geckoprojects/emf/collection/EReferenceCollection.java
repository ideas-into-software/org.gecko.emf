/**
 */
package org.geckoprojects.emf.collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EReference Collection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.geckoprojects.emf.collection.EReferenceCollection#getValues <em>Values</em>}</li>
 * </ul>
 *
 * @see org.geckoprojects.emf.collection.CollectionPackage#getEReferenceCollection()
 * @model
 * @generated
 */
public interface EReferenceCollection extends ECollection {
	/**
	 * Returns the value of the '<em><b>Values</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Values</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Values</em>' reference list.
	 * @see org.geckoprojects.emf.collection.CollectionPackage#getEReferenceCollection_Values()
	 * @model
	 * @generated
	 */
	@Override
	EList<EObject> getValues();

} // EReferenceCollection
