package org.vanduong.online_food_ordering_system.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Welcome to Online Food Ordering System");
    }

}
