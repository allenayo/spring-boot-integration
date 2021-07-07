    package com.cjp.sbj.controller;

    import com.cjp.sbj.entity.Book;
    import com.cjp.sbj.entity.Chapter;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RestController;

    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.List;

@RequestMapping("book")
@RestController
public class BookController {

    @RequestMapping(value = "/getBook", method = RequestMethod.GET)
    public Book getUser() {
        List<Chapter> chapters = new ArrayList<>(Arrays.asList(
                new Chapter(1L, "chapter1", "chapter1", 8),
                new Chapter(2L, "chpater2", "chapter2", 8)));
        return new Book(1L, "book1", "allenAyo", "eng", 10.50, 50, chapters);
    }

    @RequestMapping(value = "postBook", method = RequestMethod.POST)
    public Book postUser(@RequestBody Book book) {
        System.out.println(book);
        return book;
    }

}