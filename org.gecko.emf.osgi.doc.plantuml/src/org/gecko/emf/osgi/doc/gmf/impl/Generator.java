package org.gecko.emf.osgi.doc.gmf.impl;

import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

public class Generator {

	public static ClassModel toModel(String ecorePath) {
		EPackage ePackage = packageFromPah(ecorePath);
		ClassModel model = toModel(ePackage);
		return model;
	}

	private static EPackage packageFromPah(String ecorePath) {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore",
				new EcoreResourceFactoryImpl());
		Resource myMetaModel = resourceSet.getResource(URI.createFileURI(ecorePath), true);
		EPackage ePackage = (EPackage) myMetaModel.getContents().get(0);
		return ePackage;
	}

	private static ClassModel toModel(EPackage ePackage) {
		for (EClassifier eClassifier : ePackage.getEClassifiers()) {
			System.out.println(eClassifier);
		}
		return new ClassModel() {

			@Override
			public MNamespace namespace() {

				return toNamespace(ePackage);
			}

			@Override
			public String description() {

				return toDescription(ePackage);
			}

		};
	}

	protected static String toDescription(EModelElement eModelElement) {

		return eModelElement.getEAnnotations().stream()
				.filter(a -> "http://www.eclipse.org/emf/2002/GenModel".equals(a.getSource()))
				.map(EAnnotation::getDetails).flatMap(map -> map.entrySet().stream())
				.filter(e -> "documentation".equals(e.getKey())).findAny().map(Entry::getValue).orElse("");

	}

	protected static MNamespace toNamespace(EPackage ePackage) {

		return new MNamespace() {

			@Override
			public List<MNamespace> namespaces() {

				return ePackage.getESubpackages().stream().map(Generator::toNamespace).collect(Collectors.toList());
			}

			@Override
			public List<MClass> classes() {

				return ePackage.getEClassifiers().stream().filter(Objects::nonNull).filter(EClass.class::isInstance)
						.map(EClass.class::cast).map(Generator::toMClass).collect(Collectors.toList());

			}

			@Override
			public List<MEnum> enums() {
				return ePackage.getEClassifiers().stream().filter(Objects::nonNull).filter(EEnum.class::isInstance)
						.map(EEnum.class::cast).map(Generator::toMEnum).collect(Collectors.toList());
			};

			@Override
			public String description() {

				return toDescription(ePackage);
			}

			@Override
			public String name() {

				return ePackage.getName();
			}

			@Override
			public String base() {

				return fullyQualifiedName(ePackage);
			}

			@Override
			public String fqn() {

				return base() + "." + name();
			}

		};
	}

	private static String fullyQualifiedName(EPackage pkg) {

		return pkg.getESuperPackage() == null ? pkg.getName()
				: fullyQualifiedName(pkg.getESuperPackage()) + "." + pkg.getName();
	}

	private static MEnum toMEnum(EEnum eEnum) {

		return new MEnum() {

			@Override
			public List<MMember> members() {

				return eEnum.getELiterals().stream().map(Generator::toMMember).collect(Collectors.toList());
			}

			@Override
			public String description() {

				return toDescription(eEnum);
			}

			@Override
			public String name() {

				return eEnum.getName();
			}

			@Override
			public String base() {

				return fullyQualifiedName(eEnum.getEPackage());
			}

			@Override
			public String fqn() {
				return base() + "." + name();
			}

		};
	}

	protected static MMember toMMember(EEnumLiteral eEnumLiteral) {
		return new MMember() {

			@Override
			public String name() {

				return eEnumLiteral.getName();
			}

			@Override
			public String description() {

				return toDescription(eEnumLiteral);
			};

		};
	}

	private static MClass toMClass(EClass eClass) {

		return new MClass() {

			@Override
			public String name() {
				return eClass.getName();
			};

			@Override
			public List<MClassMember> members() {
				return eClass.getEAttributes().stream().map(Generator::toMClassMember).collect(Collectors.toList());
			}

			@Override
			public List<MRelation> references() {

				return eClass.getEReferences().stream().map(Generator::toMRelation).collect(Collectors.toList());

			}

			@Override
			public List<MRelation> supertypes() {

				return eClass.getESuperTypes().stream()
						.map(eClassSuperType -> Generator.toMRelation(eClass, eClassSuperType))
						.collect(Collectors.toList());

			}

			@Override
			public List<MClassMethod> methods() {
				return eClass.getEOperations().stream().map(Generator::toMClassMethod).collect(Collectors.toList());

			}

			@Override
			public String description() {

				return toDescription(eClass);
			};

			@Override
			public String base() {

				return fullyQualifiedName(eClass.getEPackage());
			}

			@Override
			public String fqn() {
				return base() + "." + name();
			}
		};
	}

	protected static MClassMember toMClassMember(EAttribute eAttribute) {

		return new MClassMember() {

			@Override
			public VisibilityClassifier visibilityClassifier() {

				return VisibilityClassifier.PUBLIC;
			}

			@Override
			public String type() {
				return eAttribute.getEAttributeType().getName();
			}

			@Override
			public String name() {
				return eAttribute.getName();
			}

			@Override
			public String description() {

				return toDescription(eAttribute);
			}

			@Override
			public int lowerBound() {

				return eAttribute.getLowerBound();
			}

			@Override
			public int upperBound() {
				return eAttribute.getUpperBound();
			};
		};
	}

	private static MRelation toMRelation(EReference eReference) {
		return new MRelation() {

			@Override
			public RelationType type() {

				return RelationType.DEPENDENCY;
			}

			@Override
			public MBase to() {

				return new MBase() {
					@Override
					public String fqn() {
						return base() + "." + name();
					}

					@Override
					public String name() {

						return eReference.getEType().getName();
					}

					@Override
					public String base() {
						return fullyQualifiedName(eReference.getEType().getEPackage());
					}
				};

			}

			@Override
			public MBase from() {

				return new MBase() {

					@Override
					public String name() {

						return eReference.getEType().getName();
					}

					@Override
					public String base() {
						return fullyQualifiedName(eReference.getEContainingClass().getEPackage());
					}

					@Override
					public String fqn() {
						return base() + "." + name();
					}
				};
			}

			@Override
			public String name() {
				return eReference.getName();
			}

			@Override
			public String cardinalityFrom() {

				return "1";
			}

			@Override
			public String cardinalityTo() {
				return eReference.getLowerBound() + ".." + eReference.getUpperBound();
			}

			@Override
			public String description() {

				return toDescription(eReference);
			};

		};
	}

	private static MClassMethod toMClassMethod(EOperation eOperation) {
		return new MClassMethod() {

			@Override
			public List<MParameter> paramaters() {
				return eOperation.getEParameters().stream().map(Generator::toMParameter).collect(Collectors.toList());
			}

			@Override
			public MethodClassifyer methodClassifyer() {

				return MethodClassifyer.DEFAULT;
			}

			@Override
			public String name() {
				return eOperation.getName();
			}

			@Override
			public String type() {
				return eOperation.getEType().getName();
			}

			@Override
			public VisibilityClassifier visibilityClassifier() {

				return VisibilityClassifier.PUBLIC;
			}

			@Override
			public String description() {

				return toDescription(eOperation);
			}

			@Override
			public int lowerBound() {
				return eOperation.getLowerBound();
			}

			@Override
			public int upperBound() {
				return eOperation.getUpperBound();
			};
		};
	}

	private static MParameter toMParameter(EParameter eParameter) {
		return new MParameter() {

			@Override
			public String type() {
				return eParameter.getEType().getName();
			}

			@Override
			public String name() {
				return eParameter.getName();
			}

			@Override
			public String description() {

				return toDescription(eParameter);
			};
		};
	}

	private static MRelation toMRelation(EClass eClassFrom, EClass eClassTo) {
		return new MRelation() {

			@Override
			public RelationType type() {

				return RelationType.INHERITANCE;
			}

			@Override
			public MBase to() {

				return new MBase() {

					@Override
					public String name() {

						return eClassTo.getName();
					}

					@Override
					public String base() {
						return fullyQualifiedName(eClassTo.getEPackage());
					}

					@Override
					public String fqn() {
						return base() + "." + name();
					}
				};
			}

			@Override
			public MBase from() {
				return new MBase() {

					@Override
					public String name() {

						return eClassFrom.getName();
					}

					@Override
					public String base() {
						return fullyQualifiedName(eClassFrom.getEPackage());
					}

					@Override
					public String fqn() {
						return base() + "." + name();
					}
				};
			}

			@Override
			public String name() {
				return "";
			}

			@Override
			public String cardinalityFrom() {

				return null;
			}

			@Override
			public String cardinalityTo() {
				return null;
			}

			@Override
			public String description() {

				return "";
			};

		};
	}
}
