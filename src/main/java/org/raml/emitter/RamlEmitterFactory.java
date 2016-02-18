package org.raml.emitter;

import org.raml.interfaces.emitter.IRamlEmitterFactory;

public class RamlEmitterFactory implements IRamlEmitterFactory
{
    public RamlEmitter create()
    {
        return new RamlEmitter();
    }
}
