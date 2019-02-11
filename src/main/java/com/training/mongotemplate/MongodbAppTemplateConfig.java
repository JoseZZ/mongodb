package com.training.mongotemplate;


import com.mongodb.Mongo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

import com.mongodb.MongoClient;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;


@SpringBootApplication
public class MongodbAppTemplateConfig implements CommandLineRunner {

	private static final Log log = LogFactory.getLog(MongodbAppTemplateConfig.class);
	private @Autowired MongoTemplate mongoTemplate;

	public static void main(String[] args) {
		SpringApplication.run(MongodbAppTemplateConfig.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Person p = new Person("Joe", 34);

		// Insert is used to initially store the object into the database.
		mongoTemplate.insert(p);
		log.info("Insert: " + p);

		// Find
		p = mongoTemplate.findById(p.getId(), Person.class);
		log.info("Found: " + p);

		// Update
		mongoTemplate.updateFirst(query(where("name").is("Joe")), update("age", 35), Person.class);
		p = mongoTemplate.findOne(query(where("name").is("Joe")), Person.class);
		log.info("Updated: " + p);

		// Delete
		mongoTemplate.remove(p);

		// Check that deletion worked
		List<Person> people =  mongoTemplate.findAll(Person.class);
		log.info("Number of people = : " + people.size());

		mongoTemplate.dropCollection(Person.class);
	}
}

