package com.suraev.repository;

import com.suraev.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<User, Integer> {
}
