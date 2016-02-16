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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.raml.interfaces.model.ActionType;
import org.raml.interfaces.model.IAction;
import org.raml.interfaces.model.IMimeType;
import org.raml.interfaces.model.IResponse;
import org.raml.interfaces.model.ISecurityReference;
import org.raml.interfaces.model.parameter.IAbstractParam;
import org.raml.parser.annotation.Key;
import org.raml.parser.annotation.Mapping;
import org.raml.parser.annotation.Parent;
import org.raml.parser.annotation.Scalar;
import org.raml.parser.annotation.Sequence;
import org.raml.parser.resolver.MimeTypeHandler;
import org.raml.parser.resolver.ResponseHandler;
import org.raml.parser.rule.SecurityReferenceSequenceRule;

public class Action implements Serializable, IAction
{

    private static final long serialVersionUID = 8444315314405971949L;

    @Key
    private ActionType type;

    @Scalar
    private String description;

    @Scalar
    private String displayName;

    @Mapping
    private Map<String, IAbstractParam> headers = new LinkedHashMap<String, IAbstractParam>();

    @Mapping
    private Map<String, IAbstractParam> queryParameters = new LinkedHashMap<String, IAbstractParam>();

    @Mapping(innerHandler = MimeTypeHandler.class)
    private Map<String, IMimeType> body;

    @Mapping(innerHandler = ResponseHandler.class)
    private Map<String, IResponse> responses = new LinkedHashMap<String, IResponse>();

    @Parent
    private Resource resource;

    @Sequence
    private List<String> is = new ArrayList<String>();

    @Sequence
    private List<Protocol> protocols = new ArrayList<Protocol>();

    @Sequence(rule = SecurityReferenceSequenceRule.class)
    private List<ISecurityReference> securedBy = new ArrayList<ISecurityReference>();

    @Mapping(rule = org.raml.parser.rule.UriParametersRule.class)
    private Map<String, List<IAbstractParam>> baseUriParameters = new LinkedHashMap<String, List<IAbstractParam>>();

    public Action()
    {
    }

    public ActionType getType()
    {
        return type;
    }

    public void setType(ActionType type)
    {
        this.type = type;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Map<String, IAbstractParam> getHeaders()
    {
        return headers;
    }

    public void setHeaders(Map<String, IAbstractParam> headers)
    {
        this.headers = headers;
    }

    public Map<String, IAbstractParam> getQueryParameters()
    {
        return queryParameters;
    }

    public void setQueryParameters(Map<String, IAbstractParam> queryParameters)
    {
        this.queryParameters = queryParameters;
    }

    public Map<String, IMimeType> getBody()
    {
        return body;
    }

    public void setBody(Map<String, IMimeType> body)
    {
        this.body = body;
    }

    public boolean hasBody()
    {
        return body != null && !body.isEmpty();
    }

    public Map<String, IResponse> getResponses()
    {
        return responses;
    }

    public void setResponses(Map<String, IResponse> responses)
    {
        this.responses = responses;
    }

    public Resource getResource()
    {
        return resource;
    }

    public void setResource(Resource resource)
    {
        this.resource = resource;
    }

    public List<String> getIs()
    {
        return is;
    }

    public void setIs(List<String> is)
    {
        this.is = is;
    }

    public List<Protocol> getProtocols()
    {
        return protocols;
    }

    public void setProtocols(List<Protocol> protocols)
    {
        this.protocols = protocols;
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

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    @Override
    public String toString()
    {
        return "Action{" +
               "type='" + type + '\'' +
               ", resource=" + (resource != null ? resource.getUri() : "-") + '}';
    }
}
