package com.allenayo.sbj.dao;

import com.allenayo.sbj.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}