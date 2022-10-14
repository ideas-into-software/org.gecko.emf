/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.gecko.emf.osgi.extender;

import static org.gecko.emf.osgi.EMFNamespaces.EMF_MODEL_EXTENDER_DEFAULT_PATH;
import static org.gecko.emf.osgi.EMFNamespaces.EMF_MODEL_EXTENDER_PROP_MODELS_NAME;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.osgi.framework.Bundle;
import org.osgi.framework.wiring.BundleRequirement;
import org.osgi.framework.wiring.BundleWire;
import org.osgi.framework.wiring.BundleWiring;
import org.osgi.namespace.extender.ExtenderNamespace;

public class Util {
	
	private static Logger LOGGER = Logger.getLogger(Util.class.getName());

    /**
     * Check if the bundle contains EMF models
     * @param bundle The bundle
     * @param modelBundleId The bundle id of the model bundle to check the wiring
     * @return Set of locations or {@code null}
     */
    @SuppressWarnings("unchecked")
    public static Set<String> isModelBundle(final Bundle bundle, final long modelBundleId) {
        // check for bundle wiring
        final BundleWiring bundleWiring = bundle.adapt(BundleWiring.class);
        if ( bundleWiring == null ) {
            return null;
        }

        // check for bundle requirement to implementation namespace
        final List<BundleRequirement> requirements = bundleWiring.getRequirements(ExtenderNamespace.EXTENDER_NAMESPACE);
        if ( requirements == null || requirements.isEmpty() ) {
            return null;
        }
        // get all wires for the implementation namespace
        final List<BundleWire> wires = bundleWiring.getRequiredWires(ExtenderNamespace.EXTENDER_NAMESPACE);
        for(final BundleWire wire : wires) {
            // if the wire is to this bundle (EMF extender), it must be the correct
            // requirement (no need to do additional checks like version etc.)
            if ( wire.getProviderWiring() != null
                 && wire.getProviderWiring().getBundle().getBundleId() == modelBundleId ) {
                final Object val = wire.getRequirement().getAttributes().get(EMF_MODEL_EXTENDER_PROP_MODELS_NAME);
                if ( val != null ) {
                    if ( val instanceof String ) {
                        return Collections.singleton((String)val);
                    }
                    if ( val instanceof List ) {
                        final List<String> paths = (List<String>)val;
                        final Set<String> result = new HashSet<>();
                        for(final String p : paths) {
                            result.add(p);
                        }
                        return result;
                    }
                    LOGGER.severe(()->"Attribute " + EMF_MODEL_EXTENDER_PROP_MODELS_NAME + " for EMF models requirement has an invalid type: " + val +
                                       ". Using default model path.");
                }
                return Collections.singleton(EMF_MODEL_EXTENDER_DEFAULT_PATH);
            }
        }

        return null;
    }

    public static String getSHA256(final String value) {
        try {
            final StringBuilder builder = new StringBuilder();
            final MessageDigest md = MessageDigest.getInstance("SHA-256");
            for (final byte b : md.digest(value.getBytes("UTF-8")) ) {
                builder.append(String.format("%02x", b));
            }
            return builder.toString();
        } catch ( final NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
