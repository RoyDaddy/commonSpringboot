package com.neok.commonSpringboot.repo;

import com.neok.commonSpringboot.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {
    Admin findOneById(String id);
}
