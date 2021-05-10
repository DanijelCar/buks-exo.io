package com.buks.service;

import com.buks.controller.api.BookInfoFilterRequest;
import com.buks.controller.api.BorrowBookRequest;
import com.buks.model.BookInfo;
import com.buks.model.BorrowedBookInfo;

import java.util.List;

public interface BookService {

    List<BookInfo> filterAvailableBooks( BookInfoFilterRequest bookInfoFilterRequest);

    void borrowBook(BorrowBookRequest borrowBookRequest);

    void returnBook(String bookId);
}
