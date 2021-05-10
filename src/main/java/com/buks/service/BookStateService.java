package com.buks.service;

import com.buks.controller.api.BookInfoFilterRequest;
import com.buks.controller.api.BorrowBookRequest;
import com.buks.model.BookInfo;
import com.buks.model.BorrowedBookInfo;

import java.util.List;

public interface BookStateService {
    void loadBooks(List<BookInfo> bookInfoList);

    void toBorrowedBook(BorrowBookRequest borrowBookRequest);

    void toAvailableBooks(String id);

   List<BookInfo> filterBooks( BookInfoFilterRequest bookInfoFilterRequest);
}
