package org.vanduong.online_food_ordering_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vanduong.online_food_ordering_system.model.Cart;
import org.vanduong.online_food_ordering_system.model.CartItem;
import org.vanduong.online_food_ordering_system.model.Food;
import org.vanduong.online_food_ordering_system.model.User;
import org.vanduong.online_food_ordering_system.repository.CartItemRepository;
import org.vanduong.online_food_ordering_system.repository.CartRepository;
import org.vanduong.online_food_ordering_system.repository.FoodRepository;
import org.vanduong.online_food_ordering_system.request.AddCartItemRequest;

import java.util.Optional;


@Service
public class CartServiceImp implements CartService{


    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;


    @Autowired
    private UserService userService;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FoodService foodService;


@Override
public CartItem addItemToCart(AddCartItemRequest request, String jwt) throws Exception {
    User user = userService.findUserByJwt(jwt);
    Food food = foodService.findFoodById(request.getFoodId());

    Cart cart = cartRepository.findByCustomerId(user.getId());

    for(CartItem cartItem:cart.getCartItems())
    {
        if(cartItem.getFood().equals(food))
        {
            cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
            return updateCartItemQuantity(cartItem.getId(), cartItem.getQuantity());
        }
    }

    CartItem cartItem = new CartItem();
    cartItem.setFood(food);
    cartItem.setQuantity(request.getQuantity());
    cartItem.setCart(cart);
    cartItem.setIngredients(request.getIngredients());
    cartItem.setTotalPrice(food.getPrice() * request.getQuantity());

    CartItem savedCartItem = cartItemRepository.save(cartItem);
    cart.getCartItems().add(savedCartItem);
    return savedCartItem;
}
    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
        if(cartItem.isEmpty())
        {
            throw new Exception("Cart item not found");
        }
        CartItem item= cartItem.get();
        item.setQuantity(quantity);
        item.setTotalPrice(item.getFood().getPrice() * quantity);
        return cartItemRepository.save(item);
    }

    @Override
    public Cart removeCartItem(Long cartItemId, String jwt) throws Exception {
        Cart cart = cartRepository.findByCustomerId(userService.findUserByJwt(jwt).getId());

        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
        if(cartItem.isEmpty())
        {
            throw new Exception("Cart item not found");
        }
        CartItem item= cartItem.get();
        cart.getCartItems().remove(item);
        //cartItemRepository.delete(item);
        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotal(Cart cart) throws Exception {
        Long total= 0L;

        for(CartItem cartItem:cart.getCartItems())
        {
            total += cartItem.getTotalPrice();
        }

        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        Optional<Cart> cartItem = cartRepository.findById(id);
        if(cartItem.isEmpty())
        {
            throw new Exception("Cart item not found");
        }
        return cartItem.get();
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
        //return cartRepository.findByCustomerId(userId);
        Cart cart = cartRepository.findByCustomerId(userId);
        cart.setTotal(calculateCartTotal(cart));
        return cart;
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
        Cart cart = findCartByUserId(userId);
        cart.getCartItems().clear();
        return cartRepository.save(cart);
    }
}
