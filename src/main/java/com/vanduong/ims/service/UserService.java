package org.vanduong.online_food_ordering_system.service;


import org.springframework.stereotype.Service;
import org.vanduong.online_food_ordering_system.model.User;

public interface UserService {

    public User findUserByJwt(String jwt) throws Exception;

    public User findUserByEmail(String email) throws Exception;
}
