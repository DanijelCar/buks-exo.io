package com.buks.service.impl;

import com.buks.controller.api.BookInfoFilterRequest;
import com.buks.controller.api.BorrowBookRequest;
import com.buks.model.BookInfo;
import com.buks.model.BorrowedBookInfo;
import com.buks.service.BookStateService;
import com.buks.util.ApplicationException;
import com.buks.util.PredicateBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class BookStateServiceImpl implements BookStateService {

    private Map<String, BookInfo> availableBooks = new HashMap<>();
    private Map<String, BorrowedBookInfo> borrowedBooks = new HashMap<>();
    private PredicateBuilder predicateBuilder;

    public BookStateServiceImpl(PredicateBuilder predicateBuilder) {
        this.predicateBuilder = predicateBuilder;
    }

    @Override
    public void loadBooks(List<BookInfo> bookInfoList) {
        if (availableBooks.isEmpty()) {
            availableBooks = bookInfoList.stream().collect(Collectors.toMap(BookInfo::getId, (book) -> book));
        }
    }

    @Override
    public synchronized void toBorrowedBook(BorrowBookRequest borrowBookRequest) {
        String reqBookId = borrowBookRequest.getId();
        if (availableBooks.containsKey(reqBookId)) {
            BookInfo bookInfo = availableBooks.get(reqBookId);
            BorrowedBookInfo borrowedBookInfo = mapToBorrowedBookInfo(borrowBookRequest, bookInfo);
            borrowedBooks.put(reqBookId, borrowedBookInfo);
            availableBooks.remove(reqBookId);
        } else {
            throwAppException("no available book with id " + reqBookId);
        }
    }

    @Override
    public synchronized void toAvailableBooks(String bookId) {
        if (borrowedBooks.containsKey(bookId)) {
            BorrowedBookInfo borrowedBookInfo =  borrowedBooks.get(bookId);
            availableBooks.put(bookId, borrowedBookInfo.getBookInfo());
            borrowedBooks.remove(bookId);
        } else {
            throwAppException("no borrowed book with id " + bookId);
        }
    }

    @Override
    public List<BookInfo> filterBooks( BookInfoFilterRequest bookInfoFilterRequest) {
        List<Predicate<BookInfo>> predicateBuilders = predicateBuilder.buildFilter(bookInfoFilterRequest);
        if (predicateBuilders.isEmpty()) {
            return new ArrayList<>(availableBooks.values());
        }
        return availableBooks.values().stream()
                .filter(info -> predicateBuilders.stream().allMatch(f -> f.test(info)))
                .collect(Collectors.toList());
    }

    private static void throwAppException(final String message) {
        ApplicationException.ApplicationExceptionBuilder.anApplicationException()
                .withErrorStatusCode(500)
                .withErrorMessageKey("borrowBookError")
                .withErrorDescription(message)
                .build();
    }

    private static BorrowedBookInfo mapToBorrowedBookInfo(BorrowBookRequest borrowBookRequest, BookInfo bookInfo) {
        BorrowedBookInfo borrowedBookInfo = new BorrowedBookInfo();
        borrowedBookInfo.setBookInfo(bookInfo);
        borrowedBookInfo.setBorrowedDate(LocalDateTime.now());
        borrowedBookInfo.setEmail(borrowBookRequest.getEmail());
        return borrowedBookInfo;
    }

}
