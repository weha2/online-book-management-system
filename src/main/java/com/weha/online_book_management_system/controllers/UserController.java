package com.weha.online_book_management_system.controllers;

import com.weha.online_book_management_system.dtos.DataState;
import com.weha.online_book_management_system.dtos.user.*;
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
    public ResponseEntity<DataState<String>> registerUser(@RequestBody ResponseRegisterDTO req) throws Exception {
        return ResponseEntity.ok(new DataState<>(userService.registerUser(req)));
    }

    @PostMapping("register-admin")
    public ResponseEntity<DataState<String>> registerAdmin(@RequestBody ResponseRegisterDTO req) throws Exception {
        return ResponseEntity.ok(new DataState<>(userService.registerAdmin(req)));
    }

    @PostMapping("login")
    public ResponseEntity<DataState<ResponseLoginDTO>> login(@RequestBody LoginDTO req) throws Exception {
        return ResponseEntity.ok(new DataState<>(userService.login(req)));
    }

    @PostMapping("forgot-password")
    public ResponseEntity<DataState<String>> forgotPassword(@RequestBody ForgotPasswordDTO req) {
        return ResponseEntity.ok(new DataState<>(""));
    }

    @GetMapping("refresh-token")
    public ResponseEntity<DataState<ResponseRefreshTokenDTO>> refreshToken() throws Exception {
        return ResponseEntity.ok(new DataState<>(userService.refreshToken()));
    }
}
