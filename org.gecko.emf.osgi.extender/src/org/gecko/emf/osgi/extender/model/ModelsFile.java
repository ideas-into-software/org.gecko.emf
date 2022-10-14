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

import java.net.URL;
import java.util.List;

/**
 * This object holds all models from a single file.
 * This is only an intermediate object.
 */
public class ModelsFile implements Comparable<ModelsFile> {

    private final URL url;

    private final List<Model> models;

    public ModelsFile(final URL url, final List<Model> models) {
        this.url = url;
        this.models = models;
    }

    @Override
    public int compareTo(final ModelsFile o) {
        return url.getPath().compareTo(o.url.getPath());
    }

    @Override
    public String toString() {
        return "ModelFile [url=" + url + ", models=" + models + "]";
    }

    public List<Model> getModels() {
        return this.models;
    }
}
