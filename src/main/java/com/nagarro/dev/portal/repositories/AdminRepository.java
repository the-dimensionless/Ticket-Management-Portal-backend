package com.nagarro.dev.portal.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.dev.portal.models.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

	Admin findByEmail(String email);

}
