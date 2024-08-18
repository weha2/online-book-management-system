package com.weha.online_book_management_system.base;

import com.weha.online_book_management_system.utils.TokenUtil;

public abstract class BaseService {

    private final TokenUtil tokenUtil;

    public BaseService(TokenUtil tokenUtil) {
        this.tokenUtil = tokenUtil;
    }

    protected Long userId() throws Exception {
        return tokenUtil.getUserId();
    }

    protected String principle() throws Exception {
        return tokenUtil.getPrinciple();
    }

    protected String role() {
        return tokenUtil.getRole();
    }
}
