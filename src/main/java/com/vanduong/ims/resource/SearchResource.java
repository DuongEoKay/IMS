package org.vanduong.online_food_ordering_system.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vanduong.online_food_ordering_system.dto.RestaurantDto;
import org.vanduong.online_food_ordering_system.elasticRepository.RestaurantElasticsearchRepository;
import org.vanduong.online_food_ordering_system.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchResource {

    @Autowired
    RestaurantElasticsearchRepository restaurantElasticsearchRepository;

    @GetMapping("/")
    public List<RestaurantDto> searchAll() {
        List<RestaurantDto> restaurantDtos = new ArrayList<>();
        Iterable<RestaurantDto> restaurantIterable = restaurantElasticsearchRepository.findAll();
        restaurantIterable.forEach(restaurantDtos::add);
        return restaurantDtos;
    }


    @GetMapping("/{name}")
    public List<RestaurantDto> searchByName(@PathVariable String name) {
        List<RestaurantDto> restaurantDtos = new ArrayList<>();
        Iterable<RestaurantDto> restaurantIterable = restaurantElasticsearchRepository.findByTitle(name);
        restaurantIterable.forEach(restaurantDtos::add);
        return restaurantDtos;
    }
}
