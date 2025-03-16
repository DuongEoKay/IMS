package org.vanduong.online_food_ordering_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vanduong.online_food_ordering_system.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

    public Cart findByCustomerId(Long userId);

}
