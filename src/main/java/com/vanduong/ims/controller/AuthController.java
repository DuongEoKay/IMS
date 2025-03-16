package org.vanduong.online_food_ordering_system.controller;


import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vanduong.online_food_ordering_system.config.JwtProvider;
import org.vanduong.online_food_ordering_system.model.Cart;
import org.vanduong.online_food_ordering_system.model.USER_ROLE;
import org.vanduong.online_food_ordering_system.model.User;
import org.vanduong.online_food_ordering_system.repository.CartRepository;
import org.vanduong.online_food_ordering_system.repository.UserRepository;
import org.vanduong.online_food_ordering_system.request.LoginRequest;
import org.vanduong.online_food_ordering_system.response.AuthResponse;
import org.vanduong.online_food_ordering_system.service.CustomerUserDetailsService;

import java.util.*;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;
    @Autowired
    private CartRepository cartRepository;


    @PostMapping("/signup")
    public ResponseEntity<AuthResponse>createUserHandler(@RequestBody User user)
    {
        User isEmailExist=userRepository.findByEmail(user.getEmail());
        if(isEmailExist!=null)
        {
            throw new RuntimeException("Email already exist");
        }

        User createdUser=new User();
        createdUser.setEmail(user.getEmail());
        createdUser.setFullName(user.getFullName());
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
        createdUser.setRole(user.getRole());
        User savedUser=userRepository.save(createdUser);
        Cart cart =new Cart();
        cart.setCustomer(savedUser);
        cartRepository.save(cart);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt=jwtProvider.generateToken(authentication);

        AuthResponse authResponse=new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("User created successfully");
        authResponse.setRole(createdUser.getRole());

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

@PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest)
    {
        String username=loginRequest.getEmail();

        String password=loginRequest.getPassword();

        Authentication authentication = authenticate(username,password);

        String jwt=jwtProvider.generateToken(authentication);

        AuthResponse authResponse=new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Login successfully");

        Collection<?extends GrantedAuthority>authorites=authentication.getAuthorities();
        String role =authorites.isEmpty()?null:authorites.iterator().next().getAuthority();
        authResponse.setRole(USER_ROLE.valueOf(role));

        return new ResponseEntity<>(authResponse, HttpStatus.OK);

    }
    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);

        if(userDetails==null)
        {
            throw new RuntimeException("User not found");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword()))
        {
            throw new RuntimeException("Password is incorrect");
        }
        return new  UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }




}
