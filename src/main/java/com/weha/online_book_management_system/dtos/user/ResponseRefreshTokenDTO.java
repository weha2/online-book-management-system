package com.weha.online_book_management_system.dtos.user;

import java.util.Date;

public record ResponseRefreshTokenDTO(
        String token,
        Date expiration
) {
}
