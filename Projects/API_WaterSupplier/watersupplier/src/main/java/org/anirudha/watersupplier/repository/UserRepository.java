package org.anirudha.watersupplier.repository;

import org.anirudha.watersupplier.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

