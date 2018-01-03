package com.batata.authentication.repository;

import com.batata.authentication.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends  JpaRepository<User, String> {
    User findOneByUser(String username);
}
