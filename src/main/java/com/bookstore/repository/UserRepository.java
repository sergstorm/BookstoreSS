package com.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.domain.User;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Manan
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}
