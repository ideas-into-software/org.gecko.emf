package org.geckoprojects.emf.mongo.collection;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.geckoprojects.emf.collection.CollectionFactory;
import org.geckoprojects.emf.collection.EIterable;
import org.geckoprojects.emf.collection.EReferenceCollection;
import org.geckoprojects.emf.mongo.InputContentHandler;
import org.geckoprojects.emf.mongo.Options;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;

/**
 * Implementation of InputContentHandler for EMF collections for the mongo iterator
 * @author Mark Hoffmann
 * @since 23.11.2017
 */
public class EMFCollectionInputContentHandler implements InputContentHandler {
	

	@Override
	public boolean canHandle(Map<Object, Object> options) {
		return true;
	}


	@Override
	public boolean enableResourceCache(Map<Object, Object> options) {
		return !Boolean.TRUE.equals(options.get(Options.OPTION_QUERY_CURSOR));
	}


	@Override
	public EObject createContent(FindIterable<EObject> iterable, Map<Object, Object> options) {
		return createContent(iterable, options, Collections.emptyList());
	}


	@Override
	public EObject createContent(FindIterable<EObject> iterable, Map<Object, Object> options,
			List<Resource> resourceCache) {
		boolean createCursor = Boolean.TRUE.equals(options.get(Options.OPTION_QUERY_CURSOR));
		if (createCursor) {
			EIterable eIterable = CollectionFactory.eINSTANCE.createEIterable();
			eIterable.setDelegate(iterable);
			return eIterable;
		} else {
			EReferenceCollection eCollection = CollectionFactory.eINSTANCE.createEReferenceCollection();
			InternalEList<EObject> values = (InternalEList<EObject>) eCollection.getValues();
			try(MongoCursor<EObject> mongoCursor = iterable.iterator()){
				while (mongoCursor.hasNext()){
					EObject dbObject = mongoCursor.next();
					if(Boolean.TRUE.equals(options.get(Options.OPTION_LAZY_RESULT_LOADING))){
						((InternalEObject) dbObject).eSetProxyURI(EcoreUtil.getURI(dbObject).appendQuery(null));
						detachEObject(dbObject);
					}
					if (Boolean.TRUE.equals(options.get(Options.OPTION_READ_DETACHED))) {
						detachEObject(dbObject);
					}
					values.addUnique(dbObject);
				}
			}
			return eCollection;
		}
	}
	
	/**
	 * Detaches the given {@link EObject}
	 * @param eobject the eobject instance
	 */
	private void detachEObject(EObject eobject) {
		if (eobject == null) {
			return;
		}
		Resource resource = eobject.eResource();
		if (resource != null) {
			resource.getContents().clear();
			if(resource.getResourceSet() != null){
				resource.getResourceSet().getResources().remove(resource);
			}
		}
	}

}
