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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * The model list holds all models for a single namespace
 */
public class ModelList implements Serializable, Iterable<Model> {

    private static final long serialVersionUID = 1L;

    /** Serialization version. */
    private static final int VERSION = 1;

    private List<Model> models = new ArrayList<>();

    /** The change count. */
    private volatile long changeCount = -1;

    /** Flag to indicate whether this list needs to be processed. */
    private volatile boolean hasChanges;

    /** Last installed configuration. */
    private volatile Model lastInstalled;

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
        out.writeObject(models);
        out.writeObject(lastInstalled);
        out.writeLong(changeCount);
        out.writeBoolean(hasChanges);
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
        this.models = (List<Model>) in.readObject();
        lastInstalled = (Model) in.readObject();
        this.changeCount = in.readLong();
        this.hasChanges = in.readBoolean();
    }

    /**
     * Does this list need to be processed
     * @return {@code true} if it needs processing.
     */
    public boolean hasChanges() {
        return hasChanges;
    }

    /**
     * Set the has changes flag.
     * @param value New value.
     */
    public void setHasChanges(final boolean value) {
        this.hasChanges = hasChanges;
    }

    /**
     * Add a configuration to the list.
     * @param c The configuration.
     */
    public void add(final Model c) {
        this.hasChanges = true;
        this.models.add(c);
        Collections.sort(this.models);
    }

    /**
     * Add all configurations from another list
     * @param configs The config list
     */
    public void addAll(final ModelList configs) {
        for(final Model cfg : configs) {
            // search if we already have this configuration

            for(final Model current : this.models) {
                if ( current.getBundleId() == cfg.getBundleId()
                  && current.getProperties().equals(cfg.getProperties()) ) {
                    if ( current.getState() == ModelState.UNINSTALL ) {
                        cfg.setState(ModelState.INSTALLED);
                        current.setState(ModelState.UNINSTALLED);
                    }
                    break;
                }
            }

            this.hasChanges = true;
            this.models.add(cfg);
        }
        Collections.sort(this.models);
    }

    /**
     * Get the size of the list of configurations
     * @return
     */
    public int size() {
        return this.models.size();
    }

    @Override
    public Iterator<Model> iterator() {
        return this.models.iterator();
    }

    /**
     * Get the change count.
     * @return The change count
     */
    public long getChangeCount() {
        return this.changeCount;
    }

    /**
     * Set the change count
     * @param value The new change count
     */
    public void setChangeCount(final long value) {
        this.changeCount = value;
    }

    public Model getLastInstalled() {
        return lastInstalled;
    }

    public void setLastInstalled(Model lastInstalled) {
        this.lastInstalled = lastInstalled;
    }

    /**
     * Mark configurations for a bundle uninstall
     * @param bundleId The bundle id of the uninstalled bundle
     */
    public void uninstall(final long bundleId) {
        for(final Model cfg : this.models) {
            if ( cfg.getBundleId() == bundleId ) {
                this.hasChanges = true;
                if ( cfg.getState() == ModelState.INSTALLED ) {
                    cfg.setState(ModelState.UNINSTALL);
                } else {
                    cfg.setState(ModelState.UNINSTALLED);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "ModelList [models=" + models + ", changeCount=" + changeCount + ", hasChanges="
                + hasChanges + ", lastInstalled=" + lastInstalled + "]";
    }
}
