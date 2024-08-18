package com.weha.online_book_management_system.controllers;

import com.weha.online_book_management_system.dtos.DataState;
import com.weha.online_book_management_system.dtos.order.ResponseOrderByUserDTO;
import com.weha.online_book_management_system.dtos.order.CreateOrderDTO;
import com.weha.online_book_management_system.dtos.order.ResponseOrderSuccessDTO;
import com.weha.online_book_management_system.services.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order", description = "APIs for managing orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("")
    public ResponseEntity<DataState<ResponseOrderSuccessDTO>> order(@RequestBody CreateOrderDTO req) throws Exception {
        return ResponseEntity.ok(new DataState<>(orderService.order(req)));
    }

    @GetMapping("user")
    public ResponseEntity<DataState<List<ResponseOrderByUserDTO>>> findByUser() throws Exception {
        return ResponseEntity.ok(new DataState<>(orderService.findByUser()));
    }

}
