package com.weha.online_book_management_system.services;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.weha.online_book_management_system.constans.RoleName;
import com.weha.online_book_management_system.dtos.user.LoginDTO;
import com.weha.online_book_management_system.dtos.user.ResponseLoginDTO;
import com.weha.online_book_management_system.dtos.user.ResponseRefreshTokenDTO;
import com.weha.online_book_management_system.dtos.user.ResponseRegisterDTO;
import com.weha.online_book_management_system.entity.RoleEntity;
import com.weha.online_book_management_system.entity.UserEntity;
import com.weha.online_book_management_system.repository.UserRepository;
import com.weha.online_book_management_system.utils.EmailUtil;
import com.weha.online_book_management_system.utils.TokenUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final TokenUtil tokenUtil;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            RoleService roleService,
            TokenUtil tokenUtil
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.tokenUtil = tokenUtil;
    }

    public String registerUser(ResponseRegisterDTO req) throws Exception {
        return register(req, RoleName.USER);
    }

    public String registerAdmin(ResponseRegisterDTO req) throws Exception {
        return register(req, RoleName.ADMIN);
    }

    private String register(ResponseRegisterDTO req, RoleName roleName) throws Exception {
        validateRegisterRequest(req);
        UserEntity user = new UserEntity();
        List<RoleEntity> roles = new ArrayList<>();

        Optional<RoleEntity> role = roleService.findByRoleName(roleName.name());
        if (role.isEmpty()) {
            throw new Exception("Not found role name.");
        }
        roles.add(role.get());

        user.setUsername(req.username());
        user.setPassword(passwordEncoder.encode(req.password()));
        user.setEmail(req.email());
        user.setFistName(req.firstName());
        user.setLastName(req.lastName());
        user.setRoles(roles);
        userRepository.save(user);
        return "Register successfully";
    }

    public ResponseLoginDTO login(LoginDTO req) throws Exception {
        if (Objects.isNull(req.username()) || Objects.isNull(req.password())) {
            throw new Exception("Invalid username or password!");
        }

        Optional<UserEntity> result = userRepository.findByUsername(req.username());
        if (result.isEmpty()) {
            result = userRepository.findByEmail(req.username());
        }
        if (result.isEmpty()) {
            throw new Exception("Invalid username or password!");
        }

        UserEntity user = result.get();
        if (!passwordEncoder.matches(req.password(), user.getPassword())) {
            throw new Exception("Invalid username or password!");
        }

        String token = tokenUtil.createToken(user.getId(), user.getUsername(), user.getRoles().getFirst().getRoleName());
        DecodedJWT decodedJWT = tokenUtil.decodedJWT(token);
        return new ResponseLoginDTO(user, token, decodedJWT.getExpiresAt());
    }

    public ResponseRefreshTokenDTO refreshToken() throws Exception {
        String principle = tokenUtil.getPrinciple();
        String role = tokenUtil.getRole();
        Long userId = tokenUtil.getUserId();
        String token = tokenUtil.createToken(userId, principle, role);
        Date expiration = tokenUtil.decodedJWT(token).getExpiresAt();
        return new ResponseRefreshTokenDTO(token, expiration);
    }

    private void validateRegisterRequest(ResponseRegisterDTO req) throws Exception {
        if (Objects.isNull(req.username())) {
            throw new Exception("Required username");
        }

        if (Objects.isNull(req.password())) {
            throw new Exception("Required password");
        }

        if (req.password().length() < 5) {
            throw new Exception("Password must be at least 6 characters.");
        }

        if (Objects.isNull(req.email())) {
            throw new Exception("Required email");
        }

        if (!EmailUtil.isValidEmail(req.email())) {
            throw new Exception("Email invalid");
        }

        if (userRepository.findByEmail(req.email()).isPresent()) {
            throw new Exception("This email ".concat(req.email()).concat(" is already."));
        }

        if (Objects.isNull(req.firstName())) {
            throw new Exception("Required firstname");
        }

        if (Objects.isNull(req.lastName())) {
            throw new Exception("Required lastname");
        }
    }

}
