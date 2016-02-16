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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.raml.interfaces.model.ActionType;
import org.raml.interfaces.model.IAction;
import org.raml.interfaces.model.IResource;
import org.raml.interfaces.model.ISecurityReference;
import org.raml.interfaces.model.parameter.IAbstractParam;
import org.raml.parser.annotation.Key;
import org.raml.parser.annotation.Mapping;
import org.raml.parser.annotation.Parent;
import org.raml.parser.annotation.Scalar;
import org.raml.parser.annotation.Sequence;
import org.raml.parser.resolver.ResourceHandler;
import org.raml.parser.rule.SecurityReferenceSequenceRule;

public class Resource implements Serializable, IResource
{

    private static final long serialVersionUID = -1039592210175332252L;

    @Parent
    private Resource parentResource;

    @Scalar
    private String displayName;

    @Scalar
    private String description;

    @Parent(property = "uri")
    private String parentUri;

    @Key
    private String relativeUri;

    @Mapping
    private Map<String, IAbstractParam> uriParameters = new LinkedHashMap<String, IAbstractParam>();

    @Scalar
    private String type;

    @Sequence
    private List<String> is = new ArrayList<String>();

    @Sequence(rule = SecurityReferenceSequenceRule.class)
    private List<ISecurityReference> securedBy = new ArrayList<ISecurityReference>();

    @Mapping(rule = org.raml.parser.rule.UriParametersRule.class)
    private Map<String, List<IAbstractParam>> baseUriParameters = new LinkedHashMap<String, List<IAbstractParam>>();

    @Mapping(implicit = true)
    private Map<org.raml.interfaces.model.ActionType, IAction> actions = new LinkedHashMap<org.raml.interfaces.model.ActionType, IAction>();

    @Mapping(handler = ResourceHandler.class, implicit = true)
    private Map<String, IResource> resources = new LinkedHashMap<String, IResource>();

    public Resource()
    {
    }

    public void setRelativeUri(String relativeUri)
    {
        this.relativeUri = relativeUri;
    }

    public String getParentUri()
    {
        return parentUri;
    }

    public void setParentUri(String parentUri)
    {
        this.parentUri = parentUri;
    }

    public void setUriParameters(Map<String, IAbstractParam> uriParameters)
    {
        this.uriParameters = uriParameters;
    }

    public Map<org.raml.interfaces.model.ActionType, IAction> getActions()
    {
        return actions;
    }

    public void setActions(Map<org.raml.interfaces.model.ActionType, IAction> actions)
    {
        this.actions = actions;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getRelativeUri()
    {
        return relativeUri;
    }

    public String getUri()
    {
        return parentUri + relativeUri;
    }

    public IAction getAction(org.raml.interfaces.model.ActionType name)
    {
        return actions.get(name);
    }

    public IAction getAction(String name)
    {
        return actions.get(ActionType.valueOf(name.toUpperCase()));
    }

    public Map<String, IResource> getResources()
    {
        return resources;
    }

    public void setResources(Map<String, IResource> resources)
    {
        this.resources = resources;
    }

    public Map<String, IAbstractParam> getUriParameters()
    {
        return uriParameters;
    }

    /**
     * @return URI parameters defined for the current resource plus
     * all URI parameters defined in the resource hierarchy
     */
    public Map<String, IAbstractParam> getResolvedUriParameters()
    {
        if (parentResource != null)
        {
            Map<String, IAbstractParam> uriParams = new HashMap<String, IAbstractParam>(parentResource.getResolvedUriParameters());
            uriParams.putAll(uriParameters);
            return uriParams;
        }
        return uriParameters;
    }

    public List<String> getIs()
    {
        return is;
    }

    public void setIs(List<String> is)
    {
        this.is = is;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public List<ISecurityReference> getSecuredBy()
    {
        return securedBy;
    }

    public void setSecuredBy(List<ISecurityReference> securedBy)
    {
        this.securedBy = securedBy;
    }

    public Map<String, List<IAbstractParam>> getBaseUriParameters()
    {
        return baseUriParameters;
    }

    public void setBaseUriParameters(Map<String, List<IAbstractParam>> baseUriParameters)
    {
        this.baseUriParameters = baseUriParameters;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Resource))
        {
            return false;
        }

        Resource resource = (Resource) o;

        return parentUri.equals(resource.parentUri) && relativeUri.equals(resource.relativeUri);

    }

    @Override
    public int hashCode()
    {
        int result = parentUri.hashCode();
        result = 31 * result + relativeUri.hashCode();
        return result;
    }

    @Override
    public String toString()
    {
        return "Resource{displayName='" + displayName + '\'' +
               ", uri='" + (parentUri != null ? getUri() : "-") + "'}";
    }

    public IResource getResource(String path)
    {
        for (IResource resource : resources.values())
        {
            if (path.startsWith(resource.getRelativeUri()))
            {
                if (path.length() == resource.getRelativeUri().length())
                {
                    return resource;
                }
                if (path.charAt(resource.getRelativeUri().length()) == '/')
                {
                    return resource.getResource(path.substring(resource.getRelativeUri().length()));
                }
            }
        }
        return null;
    }

    public Resource getParentResource()
    {
        return parentResource;
    }

    public void setParentResource(Resource parentResource)
    {
        this.parentResource = parentResource;
    }
}
