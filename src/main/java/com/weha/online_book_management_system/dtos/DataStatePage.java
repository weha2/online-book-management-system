package com.weha.online_book_management_system.dtos;


import org.springframework.data.domain.Page;

public record DataStatePage<T>(
        boolean isSuccess,
        T data,
        PageInfo pageInfo,
        String error
) {
    public DataStatePage(T data, Page page) {
        this(
                true,
                data,
                new PageInfo(page),
                ""
        );
    }
}

record PageInfo(
        int pageSize,
        int pageNumber,
        long itemCount,
        int pageCount
) {
    public PageInfo(Page page) {
        this(
                page.getSize(),
                page.getNumber() + 1,
                page.getTotalElements(),
                page.getTotalPages()
        );
    }
}
