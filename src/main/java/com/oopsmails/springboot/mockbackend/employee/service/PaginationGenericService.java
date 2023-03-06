package com.oopsmails.springboot.mockbackend.employee.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PaginationGenericService<T> {
    public Page<T> getPaginatedItmes(List<T> items, int pageNumber, int pageSize) {
        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = startIndex + pageSize;
        if (endIndex > items.size()) {
            endIndex = items.size();
        }
        List<T> paginatedItems = items.subList(startIndex, endIndex);
        Page<T> itemPage = new PageImpl<>(paginatedItems, PageRequest.of(pageNumber - 1, pageSize), items.size());
        log.info("returning itemPage.totalPages = {}", itemPage.getTotalPages());
        return itemPage;
    }
}
