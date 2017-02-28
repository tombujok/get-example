package com.example;

import com.hazelcast.nio.serialization.DataSerializableFactory;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;

public class ExampleFactory implements DataSerializableFactory {

    public static final int FACTORY_ID = 1;

    public IdentifiedDataSerializable create(int typeId) {
        if (typeId == 0) {
            return new ExampleProjection();
        }
        throw new IllegalArgumentException("Unsupported typeId " + typeId);
    }

}
