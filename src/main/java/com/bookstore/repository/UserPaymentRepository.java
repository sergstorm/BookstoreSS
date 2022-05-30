package com.bookstore.repository;

import com.bookstore.domain.UserPayment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserPaymentRepository extends CrudRepository<UserPayment, Long> {

    List<Object> findById(Long id);

    void deleteById(Long id);
}
