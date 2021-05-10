package com.buks.controller;

import com.buks.controller.api.BookInfoFilterRequest;
import com.buks.controller.api.BorrowBookRequest;
import com.buks.controller.api.ReturnBookRequest;
import com.buks.model.BookInfo;
import com.buks.service.BookService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookInfoController {

    BookService bookService;

    public BookInfoController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping("/filter")
    public List<BookInfo> filterAvailableBooks(@RequestBody BookInfoFilterRequest bookInfoFilterRequest){
        return bookService.filterAvailableBooks(bookInfoFilterRequest);
    }

    @PostMapping("/borrow")
    public void borrowBook(@RequestBody BorrowBookRequest borrowBookRequest){
        bookService.borrowBook(borrowBookRequest);
    }

    @PostMapping(value = "/return-book", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public void returnBook(@RequestBody ReturnBookRequest returnBookRequest){
        bookService.returnBook(returnBookRequest.getId());
    }
}
