/**
 * 
 */
package org.geckoprojects.emf.mongo.core.itest.converter;

import org.eclipse.emf.ecore.EDataType;
import org.geckoprojects.emf.example.model.basic.model.BasicPackage;
import org.geckoprojects.emf.mongo.ValueConverter;

/**
 * @author mark
 *
 */
public class NPEConverter implements ValueConverter {

	/* (non-Javadoc)
	 * @see org.gecko.emf.mongo.ValueConverter#convertMongoDBValueToEMFValue(org.eclipse.emf.ecore.EDataType, java.lang.Object)
	 */
	@Override
	public Object convertMongoDBValueToEMFValue(EDataType eDataType, Object databaseValue) {
		String npeString = databaseValue.toString();
		return new NullPointerException(npeString); 
	}

	/* (non-Javadoc)
	 * @see org.gecko.emf.mongo.ValueConverter#convertEMFValueToMongoDBValue(org.eclipse.emf.ecore.EDataType, java.lang.Object)
	 */
	@Override
	public Object convertEMFValueToMongoDBValue(EDataType eDataType, Object emfValue) {
		NullPointerException npe = (NullPointerException) emfValue;
		return npe.getMessage();
	}

	/* (non-Javadoc)
	 * @see org.gecko.emf.mongo.ValueConverter#isConverterForType(org.eclipse.emf.ecore.EDataType)
	 */
	@Override
	public boolean isConverterForType(EDataType eDataType) {
		return BasicPackage.Literals.NPE.equals(eDataType);
	}

}
