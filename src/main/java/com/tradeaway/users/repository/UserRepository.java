package com.tradeaway.users.repository;

import com.tradeaway.users.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by noumanm on 7/15/17.
 */
public interface UserRepository extends CrudRepository <User, Long> {
    List<User> findByLname(String lname);
}
