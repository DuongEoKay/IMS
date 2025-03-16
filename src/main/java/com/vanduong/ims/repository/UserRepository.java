package org.vanduong.online_food_ordering_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vanduong.online_food_ordering_system.model.User;

public interface UserRepository  extends JpaRepository<User, Long> {
    public User findByEmail(String email);
}
