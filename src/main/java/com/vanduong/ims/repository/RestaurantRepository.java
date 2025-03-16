package org.vanduong.online_food_ordering_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.vanduong.online_food_ordering_system.model.Restaurant;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("select r from Restaurant r where lower(r.name) like lower(concat('%',:query,'%'))" + "or lower(r.cuisineType)like lower(concat('%',:query,'%')) ")
    List<Restaurant> findBySearchQuery(String query);
    Restaurant findByOwnerId(Long ownerId);


}
