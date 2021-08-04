package org.geckoprojects.emf.mongo.collection;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.geckoprojects.emf.collection.CollectionFactory;
import org.geckoprojects.emf.collection.EIterable;
import org.geckoprojects.emf.mongo.InputContentHandler;
import org.geckoprojects.emf.mongo.Keywords;
import org.geckoprojects.emf.mongo.Options;
import org.geckoprojects.emf.mongo.UncachedInputContentHandler;
import org.osgi.annotation.bundle.Capability;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;

import com.mongodb.client.FindIterable;

/**
 * Implementation of InputContentHandler for EMF collections for the mongo iterator
 * @author Mark Hoffmann
 * @since 23.11.2017
 */
@Capability(
		namespace = Keywords.CAPABILITY_EXTENSION_NAMESPACE,
		name = "collection",
		version = "2.0",
		attribute = "type=default"
		)
@Component(name="EIteratorInputContentHandler", service=InputContentHandler.class, property = {Constants.SERVICE_RANKING + "=20"})
public class EMFCursorInputContentHandler extends UncachedInputContentHandler {
	

	@Override
	public boolean canHandle(Map<Object, Object> options) {
		return Boolean.TRUE.equals(options.get(Options.OPTION_QUERY_CURSOR));
	}

	@Override
	public EObject doCreateContent(FindIterable<EObject> iterable, Map<Object, Object> options) {
		EIterable eIterable = CollectionFactory.eINSTANCE.createEIterable();
		eIterable.setDelegate(iterable);
		return eIterable;
	}

}
