package com.example.v1.semojo.dao;

import com.example.v1.semojo.entities.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthDao extends JpaRepository<UserAuth, Long> {

    UserAuth findUserAuthByUsername(String username);

}
