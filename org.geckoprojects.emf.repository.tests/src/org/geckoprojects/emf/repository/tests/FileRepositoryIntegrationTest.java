package org.geckoprojects.emf.repository.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.UUID;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.geckoprojects.emf.osgi.EPackageConfigurator;
import org.geckoprojects.emf.osgi.ResourceFactoryConfigurator;
import org.geckoprojects.emf.osgi.ResourceSetFactory;
import org.geckoprojects.emf.osgi.model.test.Address;
import org.geckoprojects.emf.osgi.model.test.Person;
import org.geckoprojects.emf.osgi.model.test.TestFactory;
import org.geckoprojects.emf.osgi.model.test.configurator.TestPackageConfigurator;
import org.geckoprojects.emf.repository.EMFRepository;
import org.geckoprojects.emf.repository.file.annotations.RequireFileEMFRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.util.tracker.ServiceTracker;

@RequireFileEMFRepository
@ExtendWith(BundleContextExtension.class)
@ExtendWith(ServiceExtension.class)
public class FileRepositoryIntegrationTest {

	private final BundleContext context = FrameworkUtil.getBundle(FileRepositoryIntegrationTest.class).getBundleContext();
	private ServiceRegistration<?> testPackageRegistration;
	private FolderHelper folderHelper;

	@Before
	public void before() {
		folderHelper = new FolderHelper("EMFFileRepository/" + UUID.randomUUID().toString());
		folderHelper.initialize();
		testPackageRegistration = context.registerService(new String[] {EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName()}, new TestPackageConfigurator(), null);
	}

	@After
	public void after() {
		folderHelper.dispose();
		if (testPackageRegistration != null) {
			testPackageRegistration.unregister();
			testPackageRegistration = null;
		}
	}

	@Test
	public void testEMFRepositorySaveLoad() throws IOException, InterruptedException, InvalidSyntaxException {
		ServiceReference<ConfigurationAdmin> caRef = context.getServiceReference(ConfigurationAdmin.class);
		assertNotNull(caRef);
		ConfigurationAdmin ca = context.getService(caRef);
		assertNotNull(ca);

		ServiceReference<ResourceSetFactory> rsfRef = context.getServiceReference(ResourceSetFactory.class);
		assertNotNull(rsfRef);
		ResourceSetFactory rsf = context.getService(rsfRef);
		assertNotNull(rsf);

		ServiceReference<EMFRepository> repoRef = context.getServiceReference(EMFRepository.class);
		assertNull(repoRef);

		Configuration config = ca.createFactoryConfiguration("EMFFileRepository", "?");
		assertNotNull(config);
		Dictionary<String, Object> properties = new Hashtable<>();

		String repoId = "test_repo";
		String baseFolder = folderHelper.getFolderPath();
		properties.put(EMFRepository.PROP_ID, repoId);
		properties.put(EMFRepository.PROP_BASE_URI, baseFolder);
		properties.put(EMFRepository.PROP_CONTENT_TYPE, "ecore");
		config.update(properties);

		EMFRepository repository = getService(5000l, "(" + EMFRepository.PROP_ID + "=" + repoId + ")");
		assertNotNull(repository);

		Person person = BasicFactory.eINSTANCE.createPerson();
		person.setId("test");
		person.setFirstName("Emil");
		person.setLastName("Tester");
		URI uri = URI.createFileURI("/" + folderHelper.getFolderPath() + "/testperson.test");
		File testPersonFile = new File(folderHelper.getFolderPathFile(), "testperson.test");
		assertFalse(testPersonFile.exists());
		repository.save(person, uri);
		assertTrue(testPersonFile.exists());

		Resource r = person.eResource();
		assertNotNull(r);
		ResourceSet rs = r.getResourceSet();
		assertNotNull(rs);
		assertEquals(1, rs.getResources().size());

		repository.detach(person);
		assertNull(person.eResource());
		assertEquals(0, rs.getResources().size());

		Person personResult = repository.getEObject(uri);
		assertNotNull(personResult);
		assertNotEquals(person, personResult);
		assertNotEquals(r, personResult.eResource());

		assertTrue(EcoreUtil.equals(person, personResult));

		config.delete();

		Thread.sleep(1000l);

		repoRef = context.getServiceReference(EMFRepository.class);
		assertNull(repoRef);
	}

	@Test
	public void testEMFRepositorySaveLoadWithReference() throws IOException, InterruptedException, InvalidSyntaxException {
		ServiceReference<ConfigurationAdmin> caRef = context.getServiceReference(ConfigurationAdmin.class);
		assertNotNull(caRef);
		ConfigurationAdmin ca = context.getService(caRef);
		assertNotNull(ca);
		
		ServiceReference<ResourceSetFactory> rsfRef = context.getServiceReference(ResourceSetFactory.class);
		assertNotNull(rsfRef);
		ResourceSetFactory rsf = context.getService(rsfRef);
		assertNotNull(rsf);
		
		ServiceReference<EMFRepository> repoRef = context.getServiceReference(EMFRepository.class);
		assertNull(repoRef);
		
		Configuration config = ca.createFactoryConfiguration("EMFFileRepository", "?");
		assertNotNull(config);
		Dictionary<String, Object> properties = new Hashtable<>();
		
		String repoId = "test_repo";
		String baseFolder = folderHelper.getFolderPath();
		properties.put(EMFRepository.PROP_ID, repoId);
		properties.put(EMFRepository.PROP_BASE_URI, "file:///" + baseFolder);
		properties.put(EMFRepository.PROP_CONTENT_TYPE, "ecore");
		config.update(properties);
		
		EMFRepository repository = getService(5000l, "(" + EMFRepository.PROP_ID + "=" + repoId + ")");
		assertNotNull(repository);
		
		Address a = BasicFactory.eINSTANCE.createAddress();
		a.setId("address");
		
		repository.save(a);
		
		Person person = BasicFactory.eINSTANCE.createPerson();
		person.setId("test");
		person.setFirstName("Emil");
		person.setLastName("Tester");
		person.setAddress(a);
		URI uri = URI.createFileURI("/" + folderHelper.getFolderPath() + "/Person/test");
		File testPersonFile = new File(folderHelper.getFolderPathFile(), "/Person/test");
		repository.save(person);
		assertTrue(testPersonFile.exists());
		
		Resource r = person.eResource();
		assertNotNull(r);
		ResourceSet rs = r.getResourceSet();
		assertNotNull(rs);
		assertEquals(2, rs.getResources().size());
		
		repository.detach(person);
		assertNull(person.eResource());
		assertEquals(1, rs.getResources().size());
		
		Person personResult = repository.getEObject(uri);
		assertNotNull(personResult);
		assertNotEquals(person, personResult);
		assertNotEquals(r, personResult.eResource());
		
		assertTrue(EcoreUtil.equals(person, personResult));
		
		config.delete();
		
		Thread.sleep(1000l);
		
		repoRef = context.getServiceReference(EMFRepository.class);
		assertNull(repoRef);
	}

	@Test(expected=IllegalStateException.class)
	public void testEMFRepositorySaveLoadUnregisteredPackage() throws IOException, InterruptedException, InvalidSyntaxException {
		ServiceReference<ConfigurationAdmin> caRef = context.getServiceReference(ConfigurationAdmin.class);
		assertNotNull(caRef);
		ConfigurationAdmin ca = context.getService(caRef);
		assertNotNull(ca);

		ServiceReference<ResourceSetFactory> rsfRef = context.getServiceReference(ResourceSetFactory.class);
		assertNotNull(rsfRef);
		ResourceSetFactory rsf = context.getService(rsfRef);
		assertNotNull(rsf);

		ServiceReference<EMFRepository> repoRef = context.getServiceReference(EMFRepository.class);
		assertNull(repoRef);

		Configuration config = ca.createFactoryConfiguration("EMFFileRepository", "?");
		assertNotNull(config);
		Dictionary<String, Object> properties = new Hashtable<>();

		String repoId = "test_repo";
		String baseFolder = folderHelper.getFolderPath();
		properties.put(EMFRepository.PROP_ID, repoId);
		properties.put(EMFRepository.PROP_BASE_URI, baseFolder);
		properties.put(EMFRepository.PROP_CONTENT_TYPE, "ecore");
		config.update(properties);

		EMFRepository repository = getService(5000l, "(" + EMFRepository.PROP_ID + "=" + repoId + ")");
		assertNotNull(repository);

		Person person = BasicFactory.eINSTANCE.createPerson();
		person.setId("test");
		person.setFirstName("Emil");
		person.setLastName("Tester");
		URI uri = URI.createFileURI("/" + folderHelper.getFolderPath() + "/testperson.test");
		File testPersonFile = new File(folderHelper.getFolderPathFile(), "testperson.test");
		assertFalse(testPersonFile.exists());
		repository.save(person, uri);
		assertTrue(testPersonFile.exists());

		// unregister the EPackage for test
		testPackageRegistration.unregister();
		testPackageRegistration = null;

		Resource r = person.eResource();
		assertNotNull(r);
		ResourceSet rs = r.getResourceSet();
		assertNotNull(rs);
		assertEquals(1, rs.getResources().size());

		repository.detach(person);
		assertNull(person.eResource());
		assertEquals(0, rs.getResources().size());

		try {
			repository.getEObject(uri);
			fail("Not expected to have a return object here");
		} finally {
			config.delete();
			Thread.sleep(1000l);
			repoRef = context.getServiceReference(EMFRepository.class);
			assertNull(repoRef);
		}
	}

	@Test
	public void testEMFRepositoryNoContent() throws IOException, InterruptedException, InvalidSyntaxException {
		ServiceReference<ConfigurationAdmin> caRef = context.getServiceReference(ConfigurationAdmin.class);
		assertNotNull(caRef);
		ConfigurationAdmin ca = context.getService(caRef);
		assertNotNull(ca);

		ServiceReference<ResourceSetFactory> rsfRef = context.getServiceReference(ResourceSetFactory.class);
		assertNotNull(rsfRef);
		ResourceSetFactory rsf = context.getService(rsfRef);
		assertNotNull(rsf);

		ServiceReference<EMFRepository> repoRef = context.getServiceReference(EMFRepository.class);
		assertNull(repoRef);

		Configuration config = ca.createFactoryConfiguration("EMFFileRepository", "?");
		assertNotNull(config);
		Dictionary<String, Object> properties = new Hashtable<>();

		String repoId = "test_repo";
		String baseFolder = folderHelper.getFolderPath();
		properties.put(EMFRepository.PROP_ID, repoId);
		properties.put(EMFRepository.PROP_BASE_URI, baseFolder);
		properties.put(EMFRepository.PROP_CONTENT_TYPE, "ecore");
		config.update(properties);

		EMFRepository repository = getService(5000l, "(" + EMFRepository.PROP_ID + "=" + repoId + ")");
		assertNotNull(repository);

		URI uri = URI.createFileURI("/" + folderHelper.getFolderPath() + "/testperson.test");
		File testPersonFile = new File(folderHelper.getFolderPathFile(), "testperson.test");
		assertFalse(testPersonFile.exists());
		Person personResult = repository.getEObject(uri);
		assertNull(personResult);
		assertFalse(testPersonFile.exists());

		config.delete();

		Thread.sleep(1000l);

		repoRef = context.getServiceReference(EMFRepository.class);
		assertNull(repoRef);
	}

	<T> ServiceReference<T> getServiceReference(long timeout, String filter) throws InterruptedException, InvalidSyntaxException {
		Filter f = FrameworkUtil.createFilter(filter);
		ServiceTracker<T, T> tracker = new ServiceTracker<>(context, f, null);
		tracker.open();
		tracker.waitForService(timeout);
		return tracker.getServiceReference();
	}

	<T> T getService(long timeout, String filter) throws InterruptedException, InvalidSyntaxException {
		Filter f = FrameworkUtil.createFilter(filter);
		ServiceTracker<T, T> tracker = new ServiceTracker<>(context, f, null);
		tracker.open();
		return tracker.waitForService(timeout);
	}

}