/**
 * Copyright (c) 2012 - 2018 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package org.gecko.emf.osgi.bson.handler;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;

import de.undercouch.bson4jackson.deserializers.BsonDateDeserializer;

/**
 * De-serialization problem handler to handle BSON dates that are EMBEDDED_OBJECT_VALUES instead of String
 * @author Mark Hoffmann
 * @since 21.12.2018
 */
public class BsonDateProblemHandler extends DeserializationProblemHandler {

	/* 
	 * (non-Javadoc)
	 * @see com.fasterxml.jackson.databind.deser.DeserializationProblemHandler#handleUnexpectedToken(com.fasterxml.jackson.databind.DeserializationContext, java.lang.Class, com.fasterxml.jackson.core.JsonToken, com.fasterxml.jackson.core.JsonParser, java.lang.String)
	 */
	@Override
	public Object handleUnexpectedToken(DeserializationContext ctxt, Class<?> targetType, JsonToken t, JsonParser p,
			String failureMsg) throws IOException {
		if (targetType == Date.class && p.getCurrentToken() == JsonToken.VALUE_EMBEDDED_OBJECT) {
			BsonDateDeserializer deser = new BsonDateDeserializer();
			return deser.deserialize(p, ctxt);
		}
		return super.handleUnexpectedToken(ctxt, targetType, t, p, failureMsg);
	}
	
}
