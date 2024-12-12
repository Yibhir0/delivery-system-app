package com.devops.backend.repository;

import com.devops.backend.model.CustomType.UserType;
import com.devops.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    long countByUserType(UserType userType);

    List<User> findAllByUserType(UserType userType);
}