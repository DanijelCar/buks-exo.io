package com.buks.util;

import com.buks.controller.api.BookInfoFilterRequest;
import com.buks.model.BookInfo;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class PredicateBuilder {

    private List<Predicate<BookInfo>> filterList = new ArrayList<>();

    public List<Predicate<BookInfo>> buildFilter(BookInfoFilterRequest bookInfoFilterRequest) {
        if (bookInfoFilterRequest == null) {
            return new ArrayList<>();
        }
        if (bookInfoFilterRequest.getGenre() != null) {
            filterList.add(predicate -> predicate.getGenre().equalsIgnoreCase(bookInfoFilterRequest.getGenre()));
        }
        if (bookInfoFilterRequest.getDescription() != null) {
            filterList.add(predicate -> predicate.getDescription().contains(bookInfoFilterRequest.getDescription()));
        }
        if (bookInfoFilterRequest.getAuthor() != null) {
            filterList.add(predicate -> predicate.getAuthor().equalsIgnoreCase(bookInfoFilterRequest.getAuthor()));
        }
        if (bookInfoFilterRequest.getFromDate() != null) {
            filterList.add(predicate -> LocalDate.from(predicate.getPublishDate().toInstant()).isBefore(bookInfoFilterRequest.getFromDate()));
        }
        if (bookInfoFilterRequest.getToDate() != null) {
            filterList.add(predicate -> LocalDate.from(predicate.getPublishDate().toInstant()).isAfter(bookInfoFilterRequest.getToDate()));
        }
        return filterList;
    }
}
