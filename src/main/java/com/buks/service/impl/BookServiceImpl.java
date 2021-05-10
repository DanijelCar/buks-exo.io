package com.buks.service.impl;

import com.buks.controller.api.BookInfoFilterRequest;
import com.buks.controller.api.BorrowBookRequest;
import com.buks.model.BookInfo;
import com.buks.model.BorrowedBookInfo;
import com.buks.service.BookService;
import com.buks.service.BookStateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookStateService bookStateService;

    public BookServiceImpl(BookStateService bookStateService) {
        this.bookStateService = bookStateService;
    }

    @Override
    public List<BookInfo> filterAvailableBooks( BookInfoFilterRequest bookInfoFilterRequest) {
        return bookStateService.filterBooks(bookInfoFilterRequest);
    }

    @Override
    public void borrowBook(BorrowBookRequest borrowBookRequest) {
        bookStateService.toBorrowedBook(borrowBookRequest);
    }

    @Override
    public void returnBook(String bookId) {
        bookStateService.toAvailableBooks(bookId);
    }
}
