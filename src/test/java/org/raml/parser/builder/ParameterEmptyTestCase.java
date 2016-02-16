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
import static org.raml.interfaces.model.ActionType.GET;
import static org.raml.interfaces.model.ActionType.PUT;
import static org.raml.interfaces.model.ParamType.BOOLEAN;
import static org.raml.interfaces.model.ParamType.DATE;
import static org.raml.interfaces.model.ParamType.INTEGER;
import static org.raml.interfaces.model.ParamType.NUMBER;
import static org.raml.interfaces.model.ParamType.STRING;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.raml.interfaces.model.IRaml;
import org.raml.interfaces.model.parameter.IParameter;
import org.raml.interfaces.parser.rule.IValidationResult;

public class ParameterEmptyTestCase extends AbstractRamlTestCase
{

    private static final String ramlSource = "org/raml/params/empty-params.yaml";
    private static IRaml raml;

    @BeforeClass
    public static void init()
    {
        raml = parseRaml(ramlSource);
    }

    @Test
    public void emptyHeader()
    {
        IParameter header = raml.getResources().get("/car").getAction(PUT).getHeaders().get("enable-turbo");
        assertThat(header.getEnumeration().size(), is(3));
        assertThat(header.getEnumeration().get(0), is(""));
        List<IValidationResult> validationResults = validateRaml(ramlSource);
        assertThat(validationResults.size(), is(0));
    }


}
