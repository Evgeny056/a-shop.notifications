package com.ashopnotifications.repository;

import com.ashopnotifications.model.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
