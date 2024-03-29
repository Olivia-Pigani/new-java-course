package com.m2ibank.repository;

import com.m2ibank.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {


    Optional<Customer> findById(Long aLong);

    Optional<Customer> findByEmail(String email);
    
}
