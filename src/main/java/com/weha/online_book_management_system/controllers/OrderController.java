package com.weha.online_book_management_system.controllers;

import com.weha.online_book_management_system.constans.OrderStatus;
import com.weha.online_book_management_system.dtos.DataState;
import com.weha.online_book_management_system.dtos.order.CreateOrderDTO;
import com.weha.online_book_management_system.dtos.order.ResponseOrderDTO;
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

    @GetMapping("")
    public ResponseEntity<DataState<List<ResponseOrderDTO>>> findAllOrders() {
        return ResponseEntity.ok(new DataState<>(orderService.findAllOrders()));
    }

    @PostMapping("")
    public ResponseEntity<DataState<ResponseOrderSuccessDTO>> order(@RequestBody CreateOrderDTO req) throws Exception {
        return ResponseEntity.ok(new DataState<>(orderService.order(req)));
    }

    @GetMapping("user")
    public ResponseEntity<DataState<List<ResponseOrderDTO>>> findByUser() throws Exception {
        return ResponseEntity.ok(new DataState<>(orderService.findByUser()));
    }

    @DeleteMapping("cancel/{id}")
    public ResponseEntity<DataState<String>> cancelOrder(@PathVariable Long id) throws Exception {
        boolean result = orderService.updateOrderStatus(id, OrderStatus.CANCEL);
        return ResponseEntity.ok(new DataState<>(result ? "Cancel completed" : "Can't cancel order"));
    }

    @GetMapping("complete/{id}")
    public ResponseEntity<DataState<String>> updateStatus(@PathVariable Long id) throws Exception {
        boolean result = orderService.updateOrderStatus(id, OrderStatus.COMPLETED);
        return ResponseEntity.ok(new DataState<>(result ? "Update completed" : "Can't update order status"));
    }

}
