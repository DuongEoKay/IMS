package org.vanduong.online_food_ordering_system.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vanduong.online_food_ordering_system.config.JwtProvider;
import org.vanduong.online_food_ordering_system.model.User;
import org.vanduong.online_food_ordering_system.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtProvider jwtProvider;


    @Override
    public User findUserByJwt(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        User user = userRepository.findByEmail(email);

        if(user==null)
        {
            throw new Exception("User not found");
        }
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);

        if(user==null)
        {
            throw new Exception("User not found");
        }
        return user;
    }
}
