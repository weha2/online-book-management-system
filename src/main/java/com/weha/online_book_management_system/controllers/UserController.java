package com.weha.online_book_management_system.controllers;

import com.weha.online_book_management_system.dtos.DataState;
import com.weha.online_book_management_system.dtos.user.ForgotPasswordRequestDTO;
import com.weha.online_book_management_system.dtos.user.LoginRequestDTO;
import com.weha.online_book_management_system.dtos.user.RegisterRequestDTO;
import com.weha.online_book_management_system.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authenticate")
@Tag(name = "Authenticate", description = "APIs for managing authentication")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register-user")
    public ResponseEntity<DataState<String>> registerUser(@RequestBody RegisterRequestDTO req) {
        return ResponseEntity.ok(new DataState<>(""));
    }

    @PostMapping("register-admin")
    public ResponseEntity<DataState<String>> registerAdmin(@RequestBody RegisterRequestDTO req) {
        return ResponseEntity.ok(new DataState<>(""));
    }

    @PostMapping("login")
    public ResponseEntity<DataState<String>> login(@RequestBody LoginRequestDTO req) {
        return ResponseEntity.ok(new DataState<>(""));
    }

    @PostMapping("forgot-password")
    public ResponseEntity<DataState<String>> forgotPassword(@RequestBody ForgotPasswordRequestDTO req) {
        return ResponseEntity.ok(new DataState<>(""));
    }

    @GetMapping("refresh-token")
    public ResponseEntity<DataState<String>> refreshToken() {
        return ResponseEntity.ok(new DataState<>(""));
    }
}
