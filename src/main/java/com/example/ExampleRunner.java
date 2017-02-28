package com.example;

import com.hazelcast.config.Config;
import com.hazelcast.config.InMemoryFormat;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.SerializationConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class ExampleRunner {

    public static void main(String[] args) {

        // config
        Config config = new Config();
        MapConfig mapConfig = new MapConfig();
        mapConfig.setName("example");
        mapConfig.setInMemoryFormat(InMemoryFormat.BINARY); //use native
        mapConfig.setBackupCount(0);
        mapConfig.setAsyncBackupCount(1);
        config.addMapConfig(mapConfig);
        SerializationConfig serializationConfig = config.getSerializationConfig();
        serializationConfig.addDataSerializableFactory(ExampleFactory.FACTORY_ID, new ExampleFactory());

        // instance
        HazelcastInstance hz = Hazelcast.newHazelcastInstance(config);

        // sample data
        IMap<String, String> map = hz.getMap("example");
        map.put("1", "Nike Shoes");
        map.put("2", "Puma Shoes");
        map.put("3", "Adidas Shoes");

        // how to run the off-loaded projection
        ExampleProjection projection = new ExampleProjection();
        String result = map.get("1", projection);
        System.out.println("The result is: " + result);

        // verification
        if (!result.equals("Nike")) {
            throw new RuntimeException("Wrong result");
        }

        // shutdown
        System.exit(0);
    }


}
