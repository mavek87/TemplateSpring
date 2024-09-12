package com.matteoveroni.templatespring.repositories;

import com.matteoveroni.templatespring.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // You can define custom query methods here, if needed
}