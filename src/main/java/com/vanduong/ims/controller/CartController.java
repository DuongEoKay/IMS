package org.vanduong.online_food_ordering_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vanduong.online_food_ordering_system.model.Cart;
import org.vanduong.online_food_ordering_system.model.CartItem;
import org.vanduong.online_food_ordering_system.model.User;
import org.vanduong.online_food_ordering_system.repository.CartRepository;
import org.vanduong.online_food_ordering_system.request.AddCartItemRequest;
import org.vanduong.online_food_ordering_system.request.UpdateCartItemRequest;
import org.vanduong.online_food_ordering_system.service.CartService;
import org.vanduong.online_food_ordering_system.service.UserService;

@RestController
@RequestMapping("/cart")
public class CartController {



    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;




    @PostMapping("/")
    public ResponseEntity<CartItem> addItemToCart(@RequestBody AddCartItemRequest request, @RequestHeader("Authorization") String jwt) throws Exception {
        CartItem cartItem = cartService.addItemToCart(request, jwt);
        return ResponseEntity.ok(cartItem);
    }



    @PutMapping("/update")
    public ResponseEntity<CartItem> updateCartItemQuantity(@RequestBody UpdateCartItemRequest updateCartItemRequest) throws Exception {
        CartItem cartItem = cartService.updateCartItemQuantity(updateCartItemRequest.getCartItemId(), updateCartItemRequest.getQuantity());
        return ResponseEntity.ok(cartItem);
    }

    @DeleteMapping("/{id}/remove")
    public ResponseEntity<Long> removeCartItem( @PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception {
        Long cartId = cartService.removeCartItem(id, jwt).getId();
        return ResponseEntity.ok(cartId);
    }


    @PutMapping("/clear")
    public ResponseEntity<Cart> clearCart(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Cart cart = cartService.clearCart(user.getId());
        return ResponseEntity.ok(cart);
    }




    @GetMapping("/cart")
    public ResponseEntity<Cart> findCartByUserId(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Cart cart = cartService.findCartByUserId(user.getId());
        return ResponseEntity.ok(cart);
    }




}
