package org.vanduong.online_food_ordering_system.service;

import com.stripe.exception.StripeException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.vanduong.online_food_ordering_system.model.Order;
import org.vanduong.online_food_ordering_system.response.PaymentResponse;

public interface PaymentService  {
    public PaymentResponse createPaymentLink(Order order) throws StripeException;
}
