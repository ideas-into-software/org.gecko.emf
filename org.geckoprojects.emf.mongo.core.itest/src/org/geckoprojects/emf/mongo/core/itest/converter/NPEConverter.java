/**
 * 
 */
package org.geckoprojects.emf.mongo.core.itest.converter;

import org.eclipse.emf.ecore.EDataType;
import org.geckoprojects.emf.example.model.basic.BasicPackage;
import org.geckoprojects.emf.mongo.ValueConverter;

/**
 * @author mark
 *
 */
public class NPEConverter implements ValueConverter {


	@Override
	public Object convertMongoDBValueToEMFValue(EDataType eDataType, Object databaseValue) {
		String npeString = databaseValue.toString();
		return new NullPointerException(npeString); 
	}


	@Override
	public Object convertEMFValueToMongoDBValue(EDataType eDataType, Object emfValue) {
		NullPointerException npe = (NullPointerException) emfValue;
		return npe.getMessage();
	}

	@Override
	public boolean isConverterForType(EDataType eDataType) {
		return BasicPackage.Literals.NPE.equals(eDataType);
	}

}
