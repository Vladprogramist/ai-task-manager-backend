package com.vlad.ai_task_manager.persistance.repositories;

import com.vlad.ai_task_manager.persistance.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
