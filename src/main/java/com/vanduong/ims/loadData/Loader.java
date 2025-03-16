package org.vanduong.online_food_ordering_system.loadData;


import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;
import org.vanduong.online_food_ordering_system.dto.RestaurantDto;
import org.vanduong.online_food_ordering_system.elasticRepository.RestaurantElasticsearchRepository;
import org.vanduong.online_food_ordering_system.model.Restaurant;
import org.vanduong.online_food_ordering_system.repository.RestaurantRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Loader {


    @Autowired
    ElasticsearchOperations operations;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    RestaurantElasticsearchRepository restaurantElasticsearchRepository;


    private RestaurantDto convertRestaurantToDTO(Restaurant restaurant) {
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(restaurant.getId());
        restaurantDto.setTitle(restaurant.getName());
        restaurantDto.setDescription(restaurant.getDescription());


        return restaurantDto;
    }

    @PostConstruct
    public void loadAll() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        List<RestaurantDto> restaurantDTOs = restaurants.stream()
                .map(this::convertRestaurantToDTO)
                .collect(Collectors.toList());
        restaurantElasticsearchRepository.saveAll(restaurantDTOs);
    }

    private List<Restaurant> getData() {
        return restaurantRepository.findAll();
    }
}
