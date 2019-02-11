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
import com.mongodb.MongoClient;
import org.springframework.data.mongodb.core.query.Update;


@SpringBootApplication
public class MongodbAppTemplateConfig implements CommandLineRunner {

	private static final Log log = LogFactory.getLog(MongodbAppTemplateConfig.class);
	private @Autowired MongoTemplate mongoTemplate;

	public static void main(String[] args) {
		SpringApplication.run(MongodbAppTemplateConfig.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Person person = new Person("Bob", 38);
		log.info("Insert user: " + person);
		mongoTemplate.insert(person);
		Person one = mongoTemplate.findOne(new Query(where("name").is("Bob")), Person.class);
		log.info(one);
		// Actualizamos el elemento que acabamos de crear
		one.setName("John");
		mongoTemplate.save(one);
		one = mongoTemplate.findOne(new Query(where("name").is("John")), Person.class);
		log.info("After modify person: " + one);
		// Update first: Actualiza el primero documento que coincide
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is("John"));
		Update update = new Update();
		update.set("name", "Ralph");
		mongoTemplate.updateFirst(query, update, Person.class);
		one = mongoTemplate.findOne(new Query(where("name").is("Ralph")), Person.class);
		log.info("After updatefirst person: " + one);
		mongoTemplate.dropCollection("person");
	}
}

