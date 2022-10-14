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

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Dictionary;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.osgi.framework.ServiceRegistration;

public class Model implements Serializable, Comparable<Model> {

    private static final long serialVersionUID = 1L;

    /** Serialization version. */
    private static final int VERSION = 1;

    /** The model namespace */
    private String ns;

    /** The model ranking */
    private int ranking;

    /** The bundle id. */
    private long bundleId;

    /** The model properties. */
    private Dictionary<String, Object> properties;

    /** The index within the list of models if several. */
    private volatile int index = 0;
    
    private transient EPackage ePackage = null;
    private transient ServiceRegistration<?> modelRegistration = null;

    /** The model state. */
    private volatile ModelState state = ModelState.INSTALL;

    private volatile List<File> files;

    public Model(final EPackage ePackage,
            final Dictionary<String, Object> properties,
            final long bundleId,
            final int ranking) {
        this.ePackage = ePackage;
        this.ns = ePackage.getNsURI();
        this.ranking = ranking;
        this.bundleId = bundleId;
        this.properties = properties;
    }

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
        out.writeObject(ns);
        out.writeObject(properties);
        out.writeLong(bundleId);
        out.writeInt(ranking);
        out.writeInt(index);
        out.writeObject(state.name());
        out.writeObject(files);
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
        this.ns = (String) in.readObject();
        this.properties = (Dictionary<String, Object>) in.readObject();
        this.bundleId = in.readLong();
        this.ranking = in.readInt();
        this.index = in.readInt();
        this.state = ModelState.valueOf((String)in.readObject());
        this.files = (List<File>) in.readObject();
    }

    /**
     * Get the model namespace
     * @return The model namespace.
     */
    public String getNamespace() {
        return this.ns;
    }

    /**
     * The model ranking
     * @return The model ranking
     */
    public int getRanking() {
        return this.ranking;
    }

    /**
     * The bundle id
     * @return The bundle id
     */
    public long getBundleId() {
        return this.bundleId;
    }

    /**
     * The index of the model. This value is only
     * relevant if there are several models for the
     * same namespace with same ranking and bundle id.
     * @return The index within the model set
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Set the index
     */
    public void setIndex(final int value) {
        this.index = value;
    }

    /**
     * Get the model state
     * @return The state
     */
    public ModelState getState() {
        return this.state;
    }

    /**
     * Set the model state
     * @param value The new state
     */
    public void setState(final ModelState value) {
        this.state = value;
    }

    /**
     * Get all properties
     * @return The model properties
     */
    public Dictionary<String, Object> getProperties() {
        return this.properties;
    }

    public void setFiles(final List<File> f) {
        this.files = f;
    }

    public List<File> getFiles() {
        return this.files;
    }

    /**
	 * Returns the modelReference.
	 * @return the modelReference
	 */
	public EPackage getEPackage() {
		return ePackage;
	}

	/**
	 * Returns the modelREgistration.
	 * @return the modelREgistration
	 */
	public ServiceRegistration<?> getModelRegistration() {
		return modelRegistration;
	}

	/**
	 * Sets the modelREgistration.
	 * @param modelREgistration the modelREgistration to set
	 */
	public void setModelRegistration(ServiceRegistration<?> modelREgistration) {
		this.modelRegistration = modelREgistration;
	}

	@Override
    public int compareTo(final Model o) {
        // sort by ranking, highest first
        // if same ranking, sort by bundle id, lowest first
        // if same bundle id, sort by index
        if ( this.getRanking() > o.getRanking() ) {
            return -1;
        } else if ( this.getRanking() == o.getRanking() ) {
            if ( this.getBundleId() < o.getBundleId() ) {
                return -1;
            } else if ( this.getBundleId() == o.getBundleId() ) {
                return this.getIndex() - o.getIndex();
            }
        }
        return 1;
    }

    @Override
    public String toString() {
        return "Model [namespace=" + ns
                + ", ranking=" + ranking
                + ", bundleId=" + bundleId
                + ", index=" + index
                + ", properties=" + properties
                + ", state=" + state + "]";
    }
}
