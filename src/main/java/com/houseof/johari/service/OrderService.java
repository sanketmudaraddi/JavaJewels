package com.houseof.johari.service;

import com.houseof.johari.model.Order;
import com.houseof.johari.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Order findById(String id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElse(null);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order update(String id, Order order) {
        if (orderRepository.existsById(id)) {
            order.setId(id);
            return orderRepository.save(order);
        }
        return null;
    }

    public Order trackOrder(String id) {
        Order order = findById(id);
        if (order != null) {
            // Fetch the necessary tracking information from the order
            String orderStatus = order.getOrderStatus();
            LocalDateTime deliveryDate = order.getDeliveryDate();
            String trackingNumber = order.getTrackingNumber();
            String shippingAddress = order.getShippingAddress();

            // Set the tracking information in the order object
            order.setOrderStatus(orderStatus);
            order.setDeliveryDate(deliveryDate);
            order.setTrackingNumber(trackingNumber);
            order.setShippingAddress(shippingAddress);

            return order;
        }
        return null; // Order not found
    }

    public boolean delete(String id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
