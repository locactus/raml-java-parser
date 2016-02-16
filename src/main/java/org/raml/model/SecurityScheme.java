/*
 * Copyright 2013 (c) MuleSoft, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package org.raml.model;

import java.io.Serializable;

import org.raml.interfaces.model.ISecurityScheme;
import org.raml.interfaces.model.ISecuritySchemeDescriptor;
import org.raml.interfaces.model.ISecuritySettings;
import org.raml.parser.annotation.Scalar;

public class SecurityScheme implements Serializable, ISecurityScheme
{

    private static final long serialVersionUID = -6613409331454600471L;

    @Scalar
    private String description;

    @Scalar
    private String type;

    @Scalar
    private ISecuritySchemeDescriptor describedBy;

    @Scalar
    private ISecuritySettings settings;

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public ISecuritySchemeDescriptor getDescribedBy()
    {
        return describedBy;
    }

    public void setDescribedBy(ISecuritySchemeDescriptor describedBy)
    {
        this.describedBy = describedBy;
    }

    public ISecuritySettings getSettings()
    {
        return settings;
    }

    public void setSettings(ISecuritySettings settings)
    {
        this.settings = settings;
    }

}
