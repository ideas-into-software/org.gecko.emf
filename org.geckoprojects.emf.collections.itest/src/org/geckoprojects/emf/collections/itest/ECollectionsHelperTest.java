package org.geckoprojects.emf.collections.itest;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.geckoprojects.emf.collection.CollectionFactory;
import org.geckoprojects.emf.collection.FeaturePath;
import org.geckoprojects.emf.collection.helper.ECollectionsHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("restriction")// test bundle has set FragmentHost
public class ECollectionsHelperTest {

	private ResourceSet resourceSet = null;
	private Resource resource = null;
	private EPackage ecollPackage = null;

	@BeforeEach
	public void setup() throws IOException {

		resourceSet = new ResourceSetImpl();
		resourceSet.getPackageRegistry().put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new XMIResourceFactoryImpl());
		resource = resourceSet.createResource(URI.createURI("emfcollection.ecore"));
		assertThat(resource).isNotNull();
		InputStream collStream = getClass().getResourceAsStream("emfcollection.ecore");
		assertThat(collStream).isNotNull();
		resource.load(collStream, null);
		EObject c = resource.getContents().get(0);
		assertThat(c).isInstanceOf(EPackage.class);
		ecollPackage = (EPackage) c;
	}

	@AfterEach
	public void after() {
		resource.getContents().clear();
		resourceSet.getResources().clear();
		resourceSet = null;
	}

	@Test
	public void testGetValue() throws IOException {
		assertThat(ecollPackage).isNotNull();

		assertThat(ECollectionsHelper.getFeaturePathValue(null, null)).isNull();
		assertThat(ECollectionsHelper.getFeaturePathValue(null, ecollPackage)).isNull();

		FeaturePath fp = CollectionFactory.eINSTANCE.createFeaturePath();
		assertThat(ECollectionsHelper.getFeaturePathValue(fp, null)).isNull();
		assertThat(ECollectionsHelper.getFeaturePathValue(fp, ecollPackage)).isNull();

		fp.getFeature().add(EcorePackage.Literals.EPACKAGE__NS_PREFIX);
		assertThat(ECollectionsHelper.getFeaturePathValue(fp, ecollPackage)).hasSize(1);

		fp = CollectionFactory.eINSTANCE.createFeaturePath();
		fp.getFeature().add(EcorePackage.Literals.ESTRUCTURAL_FEATURE__DERIVED);
		assertThat(ECollectionsHelper.getFeaturePathValue(fp, ecollPackage)).isNull();

		fp = CollectionFactory.eINSTANCE.createFeaturePath();
		fp.getFeature().add(EcorePackage.Literals.EPACKAGE__NS_PREFIX);
		fp.getFeature().add(EcorePackage.Literals.ESTRUCTURAL_FEATURE__DEFAULT_VALUE);
		assertThat(ECollectionsHelper.getFeaturePathValue(fp, ecollPackage)).isNull();

		fp = CollectionFactory.eINSTANCE.createFeaturePath();
		fp.getFeature().add(EcorePackage.Literals.EPACKAGE__ECLASSIFIERS);
		fp.getFeature().add(EcorePackage.Literals.ENAMED_ELEMENT__NAME);
		assertThat(ECollectionsHelper.getFeaturePathValue(fp, ecollPackage))//
				.hasSize(9)//
				.contains("EIterable", "FeaturePath")//
				.element(0).isInstanceOf(String.class);
	}

	@Test
	public void testGetValueSelf() throws IOException {
		assertThat(ecollPackage).isNotNull();

		FeaturePath fp = CollectionFactory.eINSTANCE.createFeaturePath();
		assertThat(fp.getValue(null)).isEmpty();
		;
		assertThat(fp.getValue(ecollPackage)).isEmpty();

		fp.getFeature().add(EcorePackage.Literals.EPACKAGE__NS_PREFIX);
		assertThat(fp.getValue(ecollPackage)).hasSize(1);

		fp = CollectionFactory.eINSTANCE.createFeaturePath();
		fp.getFeature().add(EcorePackage.Literals.ESTRUCTURAL_FEATURE__DERIVED);
		assertThat(fp.getValue(ecollPackage)).isEmpty();

		fp = CollectionFactory.eINSTANCE.createFeaturePath();
		fp.getFeature().add(EcorePackage.Literals.EPACKAGE__NS_PREFIX);
		fp.getFeature().add(EcorePackage.Literals.ESTRUCTURAL_FEATURE__DEFAULT_VALUE);
		assertThat(fp.getValue(ecollPackage)).isEmpty();

		fp = CollectionFactory.eINSTANCE.createFeaturePath();
		fp.getFeature().add(EcorePackage.Literals.EPACKAGE__ECLASSIFIERS);
		fp.getFeature().add(EcorePackage.Literals.ENAMED_ELEMENT__NAME);

		assertThat(fp.getValue(ecollPackage))//
				.hasSize(9)//
				.contains("EIterable", "FeaturePath")//
				.element(0).isInstanceOf(String.class);

	}

	@Test
	public void testValidatePath() throws IOException {
		assertThat(ecollPackage).isNotNull();

		EClass ecollPackageClass = ecollPackage.eClass();

		assertThat(ECollectionsHelper.validateFeaturePath(null, null)).isFalse();
		assertThat(ECollectionsHelper.validateFeaturePath(null, ecollPackageClass)).isFalse();

		FeaturePath fp = CollectionFactory.eINSTANCE.createFeaturePath();
		assertThat(ECollectionsHelper.validateFeaturePath(fp, null)).isFalse();
		assertThat(ECollectionsHelper.validateFeaturePath(fp, ecollPackageClass)).isFalse();

		fp.getFeature().add(EcorePackage.Literals.EPACKAGE__ECLASSIFIERS);
		assertThat(ECollectionsHelper.validateFeaturePath(fp, ecollPackageClass)).isTrue();

		fp = CollectionFactory.eINSTANCE.createFeaturePath();
		fp.getFeature().add(EcorePackage.Literals.ESTRUCTURAL_FEATURE__DERIVED);
		assertThat(ECollectionsHelper.validateFeaturePath(fp, ecollPackageClass)).isFalse();

		fp = CollectionFactory.eINSTANCE.createFeaturePath();
		fp.getFeature().add(EcorePackage.Literals.EPACKAGE__NS_PREFIX);
		fp.getFeature().add(EcorePackage.Literals.ESTRUCTURAL_FEATURE__DEFAULT_VALUE);
		assertThat(ECollectionsHelper.validateFeaturePath(fp, ecollPackageClass)).isFalse();

		fp = CollectionFactory.eINSTANCE.createFeaturePath();
		fp.getFeature().add(EcorePackage.Literals.EPACKAGE__ECLASSIFIERS);
		fp.getFeature().add(EcorePackage.Literals.ENAMED_ELEMENT__NAME);
		assertThat(ECollectionsHelper.validateFeaturePath(fp, ecollPackageClass)).isTrue();

	}

	@Test
	public void testValidatePathSelf() throws IOException {
		assertThat(ecollPackage).isNotNull();

		FeaturePath fp = CollectionFactory.eINSTANCE.createFeaturePath();
		assertThat(fp.isValid(null)).isFalse();
		assertThat(fp.isValid(ecollPackage)).isFalse();

		fp.getFeature().add(EcorePackage.Literals.EPACKAGE__ECLASSIFIERS);
		assertThat(fp.isValid(ecollPackage)).isTrue();

		fp = CollectionFactory.eINSTANCE.createFeaturePath();
		fp.getFeature().add(EcorePackage.Literals.ESTRUCTURAL_FEATURE__DERIVED);
		assertThat(fp.isValid(ecollPackage)).isFalse();

		fp = CollectionFactory.eINSTANCE.createFeaturePath();
		fp.getFeature().add(EcorePackage.Literals.EPACKAGE__NS_PREFIX);
		fp.getFeature().add(EcorePackage.Literals.ESTRUCTURAL_FEATURE__DEFAULT_VALUE);
		assertThat(fp.isValid(ecollPackage)).isFalse();

		fp = CollectionFactory.eINSTANCE.createFeaturePath();
		fp.getFeature().add(EcorePackage.Literals.EPACKAGE__ECLASSIFIERS);
		fp.getFeature().add(EcorePackage.Literals.ENAMED_ELEMENT__NAME);
		assertThat(fp.isValid(ecollPackage)).isTrue();

	}

}
