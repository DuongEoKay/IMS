package org.vanduong.online_food_ordering_system.service;

import org.vanduong.online_food_ordering_system.model.Cart;
import org.vanduong.online_food_ordering_system.model.CartItem;
import org.vanduong.online_food_ordering_system.model.User;
import org.vanduong.online_food_ordering_system.request.AddCartItemRequest;

public interface CartService {
    public CartItem addItemToCart(AddCartItemRequest request, String jwt) throws  Exception;


    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception;

    public Cart removeCartItem(Long cartItemId, String jwt) throws Exception;

    public Long calculateCartTotal(Cart cart) throws Exception;

    public Cart findCartById(Long id) throws Exception;

    public Cart findCartByUserId(Long id) throws Exception;

    public  Cart clearCart(Long id) throws Exception;




}
