/**
 * This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v2.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.gecko.emf.osgi.example.model.basic.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.gecko.emf.osgi.example.model.basic.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BasicFactoryImpl extends EFactoryImpl implements BasicFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BasicFactory init() {
		try {
			BasicFactory theBasicFactory = (BasicFactory)EPackage.Registry.INSTANCE.getEFactory(BasicPackage.eNS_URI);
			if (theBasicFactory != null) {
				return theBasicFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new BasicFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BasicFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case BasicPackage.PERSON: return createPerson();
			case BasicPackage.ADDRESS: return createAddress();
			case BasicPackage.CONTACT: return createContact();
			case BasicPackage.FAMILY: return createFamily();
			case BasicPackage.BUSINESS_CONTACT: return createBusinessContact();
			case BasicPackage.TAG: return createTag();
			case BasicPackage.BUSINESS_PERSON: return createBusinessPerson();
			case BasicPackage.STRING_STRING_MAP: return (EObject)createStringStringMap();
			case BasicPackage.EMPLOYEE_INFO: return createEmployeeInfo();
			case BasicPackage.WIDGET: return createWidget();
			case BasicPackage.TEXTWIDGET: return createTextwidget();
			case BasicPackage.CONTENT: return createContent();
			case BasicPackage.HL_WIDGET: return createHLWidget();
			case BasicPackage.PERSON_CONTACT: return createPersonContact();
			case BasicPackage.PERSON_OBJECT: return createPersonObject();
			case BasicPackage.GEOMETRY: return createGeometry();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case BasicPackage.CONTACT_TYPE:
				return createContactTypeFromString(eDataType, initialValue);
			case BasicPackage.CONTACT_CONTEXT_TYPE:
				return createContactContextTypeFromString(eDataType, initialValue);
			case BasicPackage.GENDER_TYPE:
				return createGenderTypeFromString(eDataType, initialValue);
			case BasicPackage.NPE:
				return createNPEFromString(eDataType, initialValue);
			case BasicPackage.COORDINATES:
				return createCoordinatesFromString(eDataType, initialValue);
			case BasicPackage.MULTI_DIMENSION_COORDINATES:
				return createMultiDimensionCoordinatesFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case BasicPackage.CONTACT_TYPE:
				return convertContactTypeToString(eDataType, instanceValue);
			case BasicPackage.CONTACT_CONTEXT_TYPE:
				return convertContactContextTypeToString(eDataType, instanceValue);
			case BasicPackage.GENDER_TYPE:
				return convertGenderTypeToString(eDataType, instanceValue);
			case BasicPackage.NPE:
				return convertNPEToString(eDataType, instanceValue);
			case BasicPackage.COORDINATES:
				return convertCoordinatesToString(eDataType, instanceValue);
			case BasicPackage.MULTI_DIMENSION_COORDINATES:
				return convertMultiDimensionCoordinatesToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Person createPerson() {
		PersonImpl person = new PersonImpl();
		return person;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Address createAddress() {
		AddressImpl address = new AddressImpl();
		return address;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Contact createContact() {
		ContactImpl contact = new ContactImpl();
		return contact;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Family createFamily() {
		FamilyImpl family = new FamilyImpl();
		return family;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BusinessContact createBusinessContact() {
		BusinessContactImpl businessContact = new BusinessContactImpl();
		return businessContact;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Tag createTag() {
		TagImpl tag = new TagImpl();
		return tag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BusinessPerson createBusinessPerson() {
		BusinessPersonImpl businessPerson = new BusinessPersonImpl();
		return businessPerson;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, String> createStringStringMap() {
		StringStringMapImpl stringStringMap = new StringStringMapImpl();
		return stringStringMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EmployeeInfo createEmployeeInfo() {
		EmployeeInfoImpl employeeInfo = new EmployeeInfoImpl();
		return employeeInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Widget createWidget() {
		WidgetImpl widget = new WidgetImpl();
		return widget;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Textwidget createTextwidget() {
		TextwidgetImpl textwidget = new TextwidgetImpl();
		return textwidget;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Content createContent() {
		ContentImpl content = new ContentImpl();
		return content;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public HLWidget createHLWidget() {
		HLWidgetImpl hlWidget = new HLWidgetImpl();
		return hlWidget;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PersonContact createPersonContact() {
		PersonContactImpl personContact = new PersonContactImpl();
		return personContact;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PersonObject createPersonObject() {
		PersonObjectImpl personObject = new PersonObjectImpl();
		return personObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Geometry createGeometry() {
		GeometryImpl geometry = new GeometryImpl();
		return geometry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContactType createContactTypeFromString(EDataType eDataType, String initialValue) {
		ContactType result = ContactType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertContactTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContactContextType createContactContextTypeFromString(EDataType eDataType, String initialValue) {
		ContactContextType result = ContactContextType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertContactContextTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenderType createGenderTypeFromString(EDataType eDataType, String initialValue) {
		GenderType result = GenderType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertGenderTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NullPointerException createNPEFromString(EDataType eDataType, String initialValue) {
		return (NullPointerException)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertNPEToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Double[] createCoordinatesFromString(EDataType eDataType, String initialValue) {
		return (Double[])super.createFromString(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCoordinatesToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Double[][] createMultiDimensionCoordinatesFromString(EDataType eDataType, String initialValue) {
		return (Double[][])super.createFromString(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMultiDimensionCoordinatesToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BasicPackage getBasicPackage() {
		return (BasicPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static BasicPackage getPackage() {
		return BasicPackage.eINSTANCE;
	}

} //BasicFactoryImpl
