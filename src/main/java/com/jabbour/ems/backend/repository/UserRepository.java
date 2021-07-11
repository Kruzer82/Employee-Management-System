package com.jabbour.ems.backend.repository;

import com.jabbour.ems.backend.entity.Employee;
import com.jabbour.ems.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
