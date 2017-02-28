package com.example;

import com.hazelcast.executor.Offloadable;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import com.hazelcast.projection.Projection;

import java.io.IOException;

public class ExampleProjection extends Projection<String, String> implements Offloadable, IdentifiedDataSerializable {

    public String getExecutorName() {
        return ""; // can't be null, empty string means Default Executor;
    }

    public String transform(String input) {
        return input.split(" ")[0]; // just some example
    }

    public int getFactoryId() {
        return ExampleFactory.FACTORY_ID;
    }

    public int getId() {
        return 0;
    }

    public void writeData(ObjectDataOutput out) throws IOException {
    }

    public void readData(ObjectDataInput in) throws IOException {
    }
}
