package com.example.demo.config;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by HurleyD on 22/08/2017.
 */
@Configuration
@EnableTransactionManagement
public class Neo4jConfig {

    @Bean
    public org.neo4j.ogm.config.Configuration getConfiguration() {

        String url = "bolt://neo4j:entitlements@192.168.99.100:7687";

        org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
        config.driverConfiguration().setDriverClassName("org.neo4j.ogm.drivers.bolt.driver.BoltDriver").setURI(url);

        return config;
    }

    public SessionFactory getSessionFactory() {
        return new SessionFactory(getConfiguration(), "com.example.demo");
    }
}
