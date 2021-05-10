package com.buks.config;

import com.buks.model.BookInfoXmlWrapper;
import com.buks.service.BookStateService;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
class CommandLineAppStartupRunner implements CommandLineRunner {

    final BookStateService bookStateService;

    public CommandLineAppStartupRunner(BookStateService bookStateService) {
        this.bookStateService = bookStateService;
    }

    @Override
    public void run(String... args) throws Exception {
        Resource resource = new ClassPathResource("books.xml");
        XmlMapper xmlMapper = new XmlMapper();
        BookInfoXmlWrapper bookInfoXmlWrapper = xmlMapper.readValue(resource.getInputStream(), BookInfoXmlWrapper.class);
        this.bookStateService.loadBooks(bookInfoXmlWrapper.getBookInfoList());
    }
}
