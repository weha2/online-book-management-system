package com.weha.online_book_management_system.dtos;

public record DataState<T>(
        boolean isSuccess,
        T data,
        String error
) {
    public DataState(T data) {
        this(true, data, "");
    }

    public DataState(Throwable throwable) {
        this(false, null, throwable.getMessage());
    }
}
