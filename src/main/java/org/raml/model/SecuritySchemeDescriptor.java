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
import java.util.LinkedHashMap;
import java.util.Map;

import org.raml.interfaces.model.IResponse;
import org.raml.interfaces.model.ISecuritySchemeDescriptor;
import org.raml.interfaces.model.parameter.IParameter;
import org.raml.model.parameter.Header;
import org.raml.model.parameter.QueryParameter;
import org.raml.parser.annotation.Mapping;

public class SecuritySchemeDescriptor implements Serializable, ISecuritySchemeDescriptor
{

    private static final long serialVersionUID = 8752760480882952807L;

    @Mapping
    private Map<String, IParameter> headers = new LinkedHashMap<String, IParameter>();

    @Mapping
    private Map<String, IParameter> queryParameters = new LinkedHashMap<String, IParameter>();

    @Mapping
    private Map<String, IResponse> responses = new LinkedHashMap<String, IResponse>();

    public Map<String, IParameter> getHeaders()
    {
        return headers;
    }

    public void setHeaders(Map<String, IParameter> headers)
    {
        this.headers = headers;
    }

    public Map<String, IParameter> getQueryParameters()
    {
        return queryParameters;
    }

    public void setQueryParameters(Map<String, IParameter> queryParameters)
    {
        this.queryParameters = queryParameters;
    }

    public Map<String, IResponse> getResponses()
    {
        return responses;
    }

    public void setResponses(Map<String, IResponse> responses)
    {
        this.responses = responses;
    }
}
