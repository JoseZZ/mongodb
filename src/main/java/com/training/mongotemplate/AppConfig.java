package com.training.mongotemplate;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by Jose Gonzalez on 11/02/2019.
 */
public class AppConfig {

    @Bean
    public MongoClient mongo() {
        return new MongoClient("localhost");
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), "test");
    }

}
