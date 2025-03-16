package org.vanduong.online_food_ordering_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vanduong.online_food_ordering_system.dto.RestaurantDto;
import org.vanduong.online_food_ordering_system.model.Address;
import org.vanduong.online_food_ordering_system.model.ContactInformation;
import org.vanduong.online_food_ordering_system.model.Restaurant;
import org.vanduong.online_food_ordering_system.model.User;
import org.vanduong.online_food_ordering_system.repository.AddressRepository;
import org.vanduong.online_food_ordering_system.repository.RestaurantRepository;
import org.vanduong.online_food_ordering_system.repository.UserRepository;
import org.vanduong.online_food_ordering_system.request.CreateRestaurantRequest;

import javax.swing.text.html.Option;
import java.io.Console;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RestaurantServiceImp implements RestaurantService{


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;


    @Autowired
    private UserService userService;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest createRestaurantRequest, User user) {
        Address address = createRestaurantRequest.getAddress();
        address =addressRepository.save(address);

        Restaurant restaurant = new Restaurant();

        restaurant.setAddress(address);
        restaurant.setName(createRestaurantRequest.getName());
        restaurant.setContactInformation(createRestaurantRequest.getContact());
        restaurant.setOwner(user);
        restaurant.setDescription(createRestaurantRequest.getDescription());
        restaurant.setCuisineType(createRestaurantRequest.getCuisineStyle());
        restaurant.setOpeningHours(createRestaurantRequest.getOpeningHours());
        restaurant.setRegisteredAt(LocalDateTime.now());
        restaurant.setImgs(createRestaurantRequest.getImgs());

        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurantRequest) throws Exception {
        Restaurant restaurant=findRestaurantById(restaurantId);
        if(restaurant.getCuisineType()!=null)
        {
            restaurant.setCuisineType(updatedRestaurantRequest.getCuisineStyle());
        }
        if(restaurant.getName()!=null)
        {
            restaurant.setName(updatedRestaurantRequest.getName());
        }
        if(restaurant.getDescription()!=null)
        {
            restaurant.setDescription(updatedRestaurantRequest.getDescription());
        }
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {
        Restaurant restaurant=findRestaurantById(restaurantId);
        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long restaurantId) throws Exception {
        Optional<Restaurant>opt=restaurantRepository.findById(restaurantId);
        if(opt.isEmpty())
        {
            throw new Exception("Restaurant not found");
        }
        else
        {
            return opt.get();
        }
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
        Restaurant restaurant=restaurantRepository.findByOwnerId(userId);
        if(restaurant==null)
        {
            throw new Exception("Restaurant not found");
        }
        return restaurant;
    }

    @Override
    public RestaurantDto addToFavorite(Long restaurantId, User user) throws Exception {


        Restaurant restaurant=findRestaurantById(restaurantId);
        RestaurantDto restaurantDto=new RestaurantDto();
        restaurantDto.setDescription(restaurant.getDescription());
        restaurantDto.setImgs(restaurant.getImgs());
        restaurantDto.setTitle(restaurant.getName());
        restaurantDto.setId(restaurant.getId());

        boolean isFavorite=false;
        List<RestaurantDto> favorites=user.getFavourites();
        for(RestaurantDto r:favorites)
        {
            if(Objects.equals(r.getId(), restaurantId))
            {
                isFavorite=true;
                break;
            }
        }

        if(!isFavorite)
        {
            favorites.add(restaurantDto);
        }
        else {
            favorites.removeIf(r -> Objects.equals(r.getId(), restaurantId));
        }

        userRepository.save(user);
        System.out.println(user.getFavourites());
        return restaurantDto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) {
        Restaurant restaurant=restaurantRepository.findById(id).get();
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }
}
