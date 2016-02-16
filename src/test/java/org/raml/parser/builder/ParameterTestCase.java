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
package org.raml.parser.builder;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.raml.interfaces.model.ActionType.POST;

import java.util.List;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.raml.interfaces.model.ActionType;
import org.raml.interfaces.model.IRaml;
import org.raml.interfaces.model.ParamType;
import org.raml.interfaces.model.parameter.IParameter;

public class ParameterTestCase extends AbstractRamlTestCase
{

    private String ramlSource = "org/raml/params/required-param.yaml";

    @Test
    public void whenParameterIsYRequiredShouldBeTrue()
    {
        IRaml raml = parseRaml(ramlSource);
        IParameter uriParameter = raml.getBaseUriParameters().get("param2");
        assertThat(uriParameter.isRequired(), is(true));
    }

    @Test
    public void typeFile()
    {
        IRaml raml = parseRaml(ramlSource);
        IParameter queryParameter = raml.getResources().get("/resource").getAction(ActionType.GET).getQueryParameters().get("param");
        assertThat(queryParameter.getType(), is(ParamType.FILE));
    }

    @Test
    public void whenParameterHasMultiTypeOrSingleTypeShouldBeAccepted()
    {
        IRaml raml = parseRaml("org/raml/params/parameter-multi-type.yaml");

        Map<String, List<IParameter>> formParameters = raml.getResources().get("/simple").getAction(POST).getBody().get("multipart/form-data").getFormParameters();

        IParameter uriParameter = formParameters.get("acl").get(0);
        Assert.assertThat(uriParameter.getType(), CoreMatchers.is(ParamType.STRING));

        List<IParameter> file = formParameters.get("file");
        Assert.assertThat(file.size(), CoreMatchers.is(2));

        uriParameter = file.get(0);
        Assert.assertThat(uriParameter.getType(), CoreMatchers.is(ParamType.STRING));

        uriParameter = file.get(1);
        Assert.assertThat(uriParameter.getType(), CoreMatchers.is(ParamType.FILE));
    }

}
