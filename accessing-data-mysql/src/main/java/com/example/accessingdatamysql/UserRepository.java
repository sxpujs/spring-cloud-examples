package com.example.accessingdatamysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;

import com.example.accessingdatamysql.User;
import org.springframework.transaction.annotation.Transactional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

    Logger logger = LoggerFactory.getLogger(UserRepository.class);

    @Transactional
    default void batchSaveUsers(String... names) {
        for (String name : names) {
            User n = new User();
            n.setName(name);
            n.setEmail("foo@bar.com");
            this.save(n);
        }
    }
}