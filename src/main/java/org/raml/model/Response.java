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

import org.raml.interfaces.model.IMimeType;
import org.raml.interfaces.model.IResponse;
import org.raml.interfaces.model.parameter.IParameter;
import org.raml.model.parameter.Header;
import org.raml.parser.annotation.Mapping;
import org.raml.parser.annotation.Scalar;
import org.raml.parser.resolver.MimeTypeHandler;

public class Response implements Serializable, IResponse
{

    private static final long serialVersionUID = 5492447634029702499L;

    @Scalar
    private String description;

    @Mapping(innerHandler = MimeTypeHandler.class)
    private Map<String, IMimeType> body;

    @Mapping
    private Map<String, IParameter> headers = new LinkedHashMap<String, IParameter>();

    public Map<String, IParameter> getHeaders()
    {
        return headers;
    }

    public void setHeaders(Map<String, IParameter> headers)
    {
        this.headers = headers;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setBody(Map<String, IMimeType> body)
    {
        this.body = body;
    }

    public Map<String, IMimeType> getBody()
    {
        return body;
    }

    public boolean hasBody()
    {
        return body != null && !body.isEmpty();
    }
}
