package org.geckoprojects.emf.repository.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.UUID;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.geckoprojects.emf.core.api.ResourceSetFactory;
import org.geckoprojects.emf.example.model.basic.Address;
import org.geckoprojects.emf.example.model.basic.BasicFactory;
import org.geckoprojects.emf.example.model.basic.Person;
import org.geckoprojects.emf.repository.EMFRepository;
import org.geckoprojects.emf.repository.file.annotations.RequireFileEMFRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.cm.annotations.RequireConfigurationAdmin;
import org.osgi.test.common.annotation.InjectBundleContext;
import org.osgi.test.common.annotation.InjectService;
import org.osgi.test.common.service.ServiceAware;
import org.osgi.test.junit5.context.BundleContextExtension;
import org.osgi.test.junit5.service.ServiceExtension;

@RequireFileEMFRepository
@ExtendWith(BundleContextExtension.class)
@ExtendWith(ServiceExtension.class)
@RequireConfigurationAdmin
public class FileRepositoryIntegrationTest {

	@InjectBundleContext
	BundleContext context;

	@InjectService(timeout = 100000)
	ConfigurationAdmin ca;

	private FolderHelper folderHelper = new FolderHelper(null);

	@BeforeEach
	private void beforeEach() {
		folderHelper = new FolderHelper("EMFFileRepository/" + UUID.randomUUID().toString());
		folderHelper.initialize();

	}

	@Test
	public void testEMFRepositorySaveLoad(
			@InjectService(cardinality = 0, filter = "(" + EMFRepository.PROP_ID + "=" + repoId
					+ ")") ServiceAware<EMFRepository> sa)
			throws IOException, InterruptedException, InvalidSyntaxException {

		ServiceReference<ResourceSetFactory> rsfRef = context.getServiceReference(ResourceSetFactory.class);
		assertNotNull(rsfRef);
		ResourceSetFactory rsf = context.getService(rsfRef);
		assertNotNull(rsf);

		ServiceReference<EMFRepository> repoRef = context.getServiceReference(EMFRepository.class);
		assertNull(repoRef);

		Configuration config = ca.createFactoryConfiguration("EMFFileRepository", "?");
		assertNotNull(config);
		Dictionary<String, Object> properties = new Hashtable<>();

		String baseFolder = folderHelper.getFolderPath();
		properties.put(EMFRepository.PROP_ID, repoId);
		properties.put(EMFRepository.PROP_BASE_URI, baseFolder);
		properties.put(EMFRepository.PROP_CONTENT_TYPE, "ecore");
		config.update(properties);

		EMFRepository repository = sa.waitForService(5000);
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
	public void testEMFRepositorySaveLoadWithReference(
			@InjectService(cardinality = 0, filter = "(" + EMFRepository.PROP_ID + "=" + repoId
					+ ")") ServiceAware<EMFRepository> sa)
			throws IOException, InterruptedException, InvalidSyntaxException {

		ServiceReference<ResourceSetFactory> rsfRef = context.getServiceReference(ResourceSetFactory.class);
		assertNotNull(rsfRef);
		ResourceSetFactory rsf = context.getService(rsfRef);
		assertNotNull(rsf);

		ServiceReference<EMFRepository> repoRef = context.getServiceReference(EMFRepository.class);
		assertNull(repoRef);

		Configuration config = ca.createFactoryConfiguration("EMFFileRepository", "?");
		assertNotNull(config);
		Dictionary<String, Object> properties = new Hashtable<>();

		String baseFolder = folderHelper.getFolderPath();
		properties.put(EMFRepository.PROP_ID, repoId);
		properties.put(EMFRepository.PROP_BASE_URI, "file:///" + baseFolder);
		properties.put(EMFRepository.PROP_CONTENT_TYPE, "ecore");
		config.update(properties);

		EMFRepository repository = sa.waitForService(5000);
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

	@Test
	public void testEMFRepositorySaveLoadUnregisteredPackage(
			@InjectService(cardinality = 0, filter = "(" + EMFRepository.PROP_ID + "=" + repoId
					+ ")") ServiceAware<EMFRepository> sa)
			throws IOException, InterruptedException, InvalidSyntaxException, BundleException {

		ServiceReference<ResourceSetFactory> rsfRef = context.getServiceReference(ResourceSetFactory.class);
		assertNotNull(rsfRef);
		ResourceSetFactory rsf = context.getService(rsfRef);
		assertNotNull(rsf);

		ServiceReference<EMFRepository> repoRef = context.getServiceReference(EMFRepository.class);
		assertNull(repoRef);

		Configuration config = ca.createFactoryConfiguration("EMFFileRepository", "?");
		assertNotNull(config);
		Dictionary<String, Object> properties = new Hashtable<>();

		String baseFolder = folderHelper.getFolderPath();
		properties.put(EMFRepository.PROP_ID, repoId);
		properties.put(EMFRepository.PROP_BASE_URI, baseFolder);
		properties.put(EMFRepository.PROP_CONTENT_TYPE, "ecore");
		config.update(properties);

		EMFRepository repository = sa.waitForService(5000);
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

		config.delete();
		Thread.sleep(1000l);
		repoRef = context.getServiceReference(EMFRepository.class);
		assertNull(repoRef);

	}

	private static final String repoId = "test_repo";

	@Test
	public void testEMFRepositoryNoContent(
			@InjectService(cardinality = 0, filter = "(" + EMFRepository.PROP_ID + "=" + repoId
					+ ")") ServiceAware<EMFRepository> sa)
			throws IOException, InterruptedException, InvalidSyntaxException {

		ServiceReference<ResourceSetFactory> rsfRef = context.getServiceReference(ResourceSetFactory.class);
		assertNotNull(rsfRef);
		ResourceSetFactory rsf = context.getService(rsfRef);
		assertNotNull(rsf);

		ServiceReference<EMFRepository> repoRef = context.getServiceReference(EMFRepository.class);
		assertNull(repoRef);

		Configuration config = ca.createFactoryConfiguration("EMFFileRepository", "?");
		assertNotNull(config);
		Dictionary<String, Object> properties = new Hashtable<>();

		String baseFolder = folderHelper.getFolderPath();
		properties.put(EMFRepository.PROP_ID, repoId);
		properties.put(EMFRepository.PROP_BASE_URI, baseFolder);
		properties.put(EMFRepository.PROP_CONTENT_TYPE, "ecore");
		config.update(properties);

		EMFRepository repository = sa.waitForService(5000l);
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

}