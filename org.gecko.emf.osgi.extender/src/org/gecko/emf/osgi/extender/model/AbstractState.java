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
package org.gecko.emf.osgi.extender.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * This object holds a sorted map of configurations
 */
public class AbstractState implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Serialization version. */
    private static final int VERSION = 1;

    private Map<String, ModelList> modelsByNamespace = new TreeMap<>();

    /**
     * Serialize the object
     * - write version id
     * - serialize fields
     * @param out Object output stream
     * @throws IOException
     */
    private void writeObject(final java.io.ObjectOutputStream out)
    throws IOException {
        out.writeInt(VERSION);
        out.writeObject(modelsByNamespace);
    }

    /**
     * Deserialize the object
     * - read version id
     * - deserialize fields
     */
    @SuppressWarnings("unchecked")
    private void readObject(final java.io.ObjectInputStream in)
    throws IOException, ClassNotFoundException {
        final int version = in.readInt();
        if ( version < 1 || version > VERSION ) {
            throw new ClassNotFoundException(this.getClass().getName());
        }
        this.modelsByNamespace = (Map<String, ModelList>) in.readObject();
    }

    public void add(final Model c) {
        ModelList configs = this.modelsByNamespace.get(c.getNamespace());
        if ( configs == null ) {
            configs = new ModelList();
            this.modelsByNamespace.put(c.getNamespace(), configs);
        }

        configs.add(c);
    }

    public Map<String, ModelList> getModels() {
        return this.modelsByNamespace;
    }

    public ModelList getModels(final String pid) {
        return this.getModels().get(pid);
    }

    public Collection<String> getNamespaces() {
        return this.getModels().keySet();
    }
}
