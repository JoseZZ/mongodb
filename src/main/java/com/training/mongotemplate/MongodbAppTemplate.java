package com.training.mongotemplate;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import com.mongodb.MongoClient;


@SpringBootApplication
public class MongodbAppTemplate implements CommandLineRunner {

	private static final Log log = LogFactory.getLog(MongodbAppTemplate.class);

	public static void main(String[] args) {
		SpringApplication.run(MongodbAppTemplate.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		MongoOperations mongoOps = new MongoTemplate(new MongoClient(), "database");
		Person person = new Person("Joe", 34);
		log.info("Insert user: " + person);
		mongoOps.insert(person);
		log.info(mongoOps.findOne(new Query(where("name").is("Joe")), Person.class));
		mongoOps.dropCollection("person");
	}
}

