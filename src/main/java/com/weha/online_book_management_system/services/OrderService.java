package com.weha.online_book_management_system.services;

import com.weha.online_book_management_system.base.BaseService;
import com.weha.online_book_management_system.constans.OrderStatus;
import com.weha.online_book_management_system.dtos.book.ResponseBookDTO;
import com.weha.online_book_management_system.dtos.order.CreateOrderDTO;
import com.weha.online_book_management_system.dtos.order.ResponseOrderDTO;
import com.weha.online_book_management_system.dtos.order.ResponseOrderSuccessDTO;
import com.weha.online_book_management_system.entity.BookEntity;
import com.weha.online_book_management_system.entity.OrderEntity;
import com.weha.online_book_management_system.entity.UserEntity;
import com.weha.online_book_management_system.repository.OrderRepository;
import com.weha.online_book_management_system.utils.TokenUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService extends BaseService {

    private final OrderRepository orderRepository;
    private final BookService bookService;

    public OrderService(
            OrderRepository orderRepository,
            TokenUtil tokenUtil,
            BookService bookService
    ) {
        super(tokenUtil);
        this.orderRepository = orderRepository;
        this.bookService = bookService;
    }

    public List<ResponseOrderDTO> findAllOrders() {
        return orderRepository
                .findAll()
                .stream()
                .map(ResponseOrderDTO::new)
                .toList();
    }

    public ResponseOrderSuccessDTO order(CreateOrderDTO req) throws Exception {
        validateOrderRequest(req);

        UserEntity user = new UserEntity();
        user.setId(userId());

        ResponseBookDTO book = bookService.findBookById(req.bookId());
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(book.id());

        OrderEntity order = new OrderEntity();
        order.setUser(user);
        order.setBook(bookEntity);
        order.setQuantity(req.quantity());
        order.setPriceAtOrder(book.price());
        order.setStatus(OrderStatus.PENDING.name());

        OrderEntity result = orderRepository.save(order);

        return new ResponseOrderSuccessDTO(
                true,
                "Order successfully",
                OrderStatus.PENDING.name(),
                result.getCreatedDate()
        );
    }

    public List<ResponseOrderDTO> findByUser() throws Exception {
        UserEntity user = new UserEntity();
        user.setId(userId());
        Optional<List<OrderEntity>> orders = orderRepository.findByUser(user);
        return orders
                .map(orderEntities -> orderEntities
                        .stream()
                        .map(ResponseOrderDTO::new)
                        .toList()
                )
                .orElseGet(ArrayList::new);
    }

    public boolean updateOrderStatus(Long id, OrderStatus status) throws Exception {
        Optional<OrderEntity> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new Exception("Not found order width id ".concat(String.valueOf(id)));
        }
        OrderEntity entity = order.get();
        if (entity.getStatus().equals(OrderStatus.CANCEL.name())) {
            throw new Exception("Can not update order status");
        }
        entity.setStatus(status.name());
        orderRepository.save(entity);
        return true;
    }

    private void validateOrderRequest(CreateOrderDTO req) throws Exception {
        if (req.quantity() < 1) {
            throw new Exception("Invalid quantity");
        }
    }
}
