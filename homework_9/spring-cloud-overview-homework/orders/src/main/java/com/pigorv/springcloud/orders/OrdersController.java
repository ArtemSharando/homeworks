package com.pigorv.springcloud.orders;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.stream.Collectors.toList;

@RequestMapping
@RestController
public class OrdersController {
    private List<Order> orderList = new ArrayList<>();

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    ClientOrder clientOrder;

    @Autowired
    ProductOrder productOrder;

    @Autowired
    NotificationOrder notificationOrder;

    @GetMapping
    public String health() {
        return "OK";
    }

    @PostMapping
    public ResponseEntity<Order> createNewOrder(@RequestBody Order order) {
        productOrder.getProduct(order.getProduct());
        orderList.add(order);
        notificationOrder.createNotification(order.getUserName());
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/users/{userName}")
    public List<String> getProductsForUser(@PathVariable String userName) {
        return orderList.stream()
                .filter(order -> userName.equals(order.getUserName()))
                .map(Order::getProduct)
                .collect(toList());
    }
}
