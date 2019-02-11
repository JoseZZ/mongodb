package com.training.mongorepository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Jose Gonzalez on 11/02/2019.
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {

    public Customer findByFirstName(String firstName);
    public List<Customer> findByLastName(String lastName);

}