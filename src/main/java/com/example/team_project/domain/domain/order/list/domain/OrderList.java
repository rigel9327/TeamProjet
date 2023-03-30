package com.example.team_project.domain.domain.order.list.domain;

import com.example.team_project.domain.domain.order.item.domain.Order;
import com.example.team_project.domain.domain.order.list.Address;
import com.example.team_project.domain.domain.user.domain.User;
import com.example.team_project.enums.OrderStatus;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "order_lists")
public class OrderList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address")
    private Address address;

    @Column(nullable = false)
    private String paymentMethod;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "order_status")
    private OrderStatus status;

    private double totalPrice;

    public OrderList(){}

    public OrderList(User user, Address address, String paymentMethod){
        this.user = user;
        this.address = address;
        this.paymentMethod = paymentMethod;
        this.status = OrderStatus.ORDERED;
    }

    public void cancel(User user){
        this.user = user;
        this.status = OrderStatus.CANCELED;
    }
}
