package com.buks.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "catalog")
public class BookInfoXmlWrapper {
    @JacksonXmlElementWrapper(localName = "book", useWrapping = false)
    private List<BookInfo> book = new ArrayList<>();

    public List<BookInfo> getBookInfoList() {
        return book;
    }

    public void setBookInfoList(List<BookInfo> book) {
        this.book = book;
    }
}
