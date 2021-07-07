package org.geckoprojects.emf.mongo.handlers;

import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.geckoprojects.emf.core.api.EMFNamespaces;
import org.geckoprojects.emf.core.api.ResourceSetConfigurator;
import org.geckoprojects.emf.mongo.InputStreamFactory;
import org.geckoprojects.emf.mongo.OutputStreamFactory;
import org.geckoprojects.mongo.core.MongoConstants;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

import com.mongodb.client.MongoDatabase;

/**
 * This implementation of the ResourceSetConfigurator service will attach
 * all currently bound URI handlers to the ResourceSet. This service is
 * intended to be used with the IResourceSetFactory service.
 * 
 * @author bhunt
 * 
 */
@Component(name="MongoResourceSetConfiguratorComponent", immediate=true)
public class MongoResourceSetConfiguratorComponent {

	public static final String PROP_MONGO_ALIAS = "database.alias";
	private final Map< MongoDatabase,Map<String,Object>> mongoDatabases = new ConcurrentHashMap< MongoDatabase,Map<String,Object>>();
	private ServiceRegistration<ResourceSetConfigurator> configuratorRegistration;
	private MongoURIHandlerProvider uriHandlerProvider = new MongoURIHandlerProvider();
	private BundleContext ctx;
	private List<String> aliases = new LinkedList<String>();
	private Map<String, String> aliasIdentifierMap = new HashMap<String, String>();

	/**
	 * Called on component activation
	 * @param context the component context
	 */
	@Activate
	public void activate(BundleContext context) {
		ctx = context;
		Dictionary<String, Object> properties = getDictionary();
		configuratorRegistration = ctx.registerService(ResourceSetConfigurator.class, new MongoResourceSetConfigurator(uriHandlerProvider), properties);
	}

	/**
	 * Called on component deactivation
	 */
	@Deactivate
	public void deactivate() {
		configuratorRegistration.unregister();
		configuratorRegistration = null;
	}

	/**
	 * Adds a {@link MongoDatabase} to the provider map.  
	 * @param mongoDatabase the provider to be added
	 */
	@Reference(name="MongoDatabase", policy=ReferencePolicy.DYNAMIC, cardinality=ReferenceCardinality.AT_LEAST_ONE)
	public void addMongoDatabase(MongoDatabase mongoDatabase, Map<String, Object> properties) {
		
		mongoDatabases.put( mongoDatabase,properties);
		uriHandlerProvider.addMongoDatabaseProvider(mongoDatabase);
		updateProperties(MongoConstants.DB_PROP_DATABASE_UID, properties, true);
	}

	/**
	 * Removes a {@link MongoDatabase} from the map 
	 * @param mongoDatabase the provider to be removed
	 */
	public void removeMongoDatabase(MongoDatabase mongoDatabase, Map<String, Object> properties) {
	
		Map<String,Object> map=mongoDatabases.remove(mongoDatabase);
		uriHandlerProvider.removeMongoDatabaseProvider(mongoDatabase);
		updateProperties(MongoConstants.DB_PROP_DATABASE_UID, map, false);
	}

	/**
	 * Sets an {@link InputStreamFactory} to handle input streams
	 * @param inputStreamFactory the factory to set
	 */
	@Reference(name="InputStreamFactory", cardinality=ReferenceCardinality.MANDATORY, policy=ReferencePolicy.STATIC)
	public void setInputStreamFactory(InputStreamFactory inputStreamFactory) {
		uriHandlerProvider.setInputStreamFactory(inputStreamFactory);
	}

	/**
	 * Sets an {@link OutputStreamFactory} to handle output streams
	 * @param outputStreamFactory the factory to set
	 */
	@Reference(name="OutputStreamFactory", cardinality=ReferenceCardinality.MANDATORY, policy=ReferencePolicy.STATIC)
	public void setOutputStreamFactory(OutputStreamFactory outputStreamFactory) {
		uriHandlerProvider.setOutputStreamFactory(outputStreamFactory);
	}

	/**
	 * Updates the properties of the service, depending on changes on injected services
	 * @param type the type of the property to publish 
	 * @param serviceProperties the service properties from the injected service
	 * @param add <code>true</code>, if the service was add, <code>false</code> in case of an remove
	 */
	private void updateProperties(String type, Map<String, Object> serviceProperties, boolean add) {
		Object name = serviceProperties.get(type);
		Object identifier = serviceProperties.get(MongoConstants.DB_PROP_DATABASE_INTERNAL_NAME);
		
		if (Objects.equals(type, MongoConstants.DB_PROP_DATABASE_UID)) {
			if (add) {
				aliases.add(name.toString());
				if (identifier != null && identifier instanceof String) {
					aliasIdentifierMap.put(name.toString(), identifier.toString());
				}
			} else {
				aliases.remove(name.toString());
				aliasIdentifierMap.remove(name.toString());
			}

		}
		updateRegistrationProperties();
	
	}

	/**
	 * Updates the service registration properties
	 */
	private void updateRegistrationProperties() {
		if (configuratorRegistration != null) {
			configuratorRegistration.setProperties(getDictionary());
		}
	}

	/**
	 * Creates a dictionary for the stored properties
	 * @return a dictionary for the stored properties
	 */
	private Dictionary<String, Object> getDictionary() {
		Dictionary<String, Object> properties = new Hashtable<>();
		List<String> aliasList = new LinkedList<String>(aliases);
		String[] aliasNames = aliasList.toArray(new String[0]);
		if (aliasNames.length > 0) {
			properties.put(MongoConstants.DB_PROP_DATABASE_UID, aliasNames);
		}
		String[] ids = aliasList.stream()
				.map(this::replaceWithIdentifier)
				.collect(Collectors.toList())
				.toArray(new String[0]);
		String[] configNames = Arrays.copyOf(ids, ids.length + 1);
		configNames[ids.length] = "mongo";
 		properties.put(EMFNamespaces.EMF_CONFIGURATOR_NAME, configNames);
		return properties;
	}
	
	/**
	 * Replaces the given alias with an identifier {@link MongoDatabaseProvider#PROP_DATABASE_IDENTIFIER} value
	 * if it exists
	 * @param alias the alias
	 * @return the identifier, if it exists, otherwise the alias
	 */
	private String replaceWithIdentifier(String alias) {
		String id = aliasIdentifierMap.get(alias);
		if (id != null && !id.isEmpty()) {
			return id;
		} else {
			return alias;
		}
	}
	
}
