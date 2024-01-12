package com.example.adapters.dbConfig;

import com.datastax.oss.driver.api.core.CqlSession;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;

@Factory
public class CassandraConfig{

    @Bean
    public CqlSession cqlSession() {
        // Specify your Cassandra configuration, including keyspace
        return CqlSession.builder()
//                .addContactPoint(new InetSocketAddress("127.0.0.1", 9042))
                .withKeyspace("utility")
                // Add other Cassandra configuration as needed
                .build();
    }
}


