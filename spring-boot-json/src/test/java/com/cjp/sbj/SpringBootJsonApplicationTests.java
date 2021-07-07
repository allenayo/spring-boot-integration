package com.cjp.sbj;

import com.cjp.sbj.entity.Book;
import com.cjp.sbj.entity.Chapter;
import com.cjp.sbj.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SpringBootJsonApplicationTests {

    @Test
    public void testToJson() throws Exception {
        List<Chapter> chapters = new ArrayList<>(Arrays.asList(
                new Chapter(1L, "chapter1", "chapter1", 8),
                new Chapter(2L, "chpater2", "chapter2", 8)));
        Book book = new Book(1L, "book1", "allenAyo", "eng", 10.50, 50, chapters);
        System.out.println(JsonUtil.toJson(book));
    }

    @Test
    public void testToBean() throws Exception {
        String jsonString = "{\n" +
                "  \"id\" : 1,\n" +
                "  \"name\" : \"book1\",\n" +
                "  \"author\" : \"allenAyo\",\n" +
                "  \"lang\" : \"eng\",\n" +
                "  \"price\" : 10.5,\n" +
                "  \"totalPages\" : 50,\n" +
                "  \"chapters\" : [ {\n" +
                "    \"id\" : 1,\n" +
                "    \"title\" : \"chapter1\",\n" +
                "    \"content\" : \"chapter1\",\n" +
                "    \"words\" : 8\n" +
                "  }, {\n" +
                "    \"id\" : 2,\n" +
                "    \"title\" : \"chpater2\",\n" +
                "    \"content\" : \"chapter2\",\n" +
                "    \"words\" : 8\n" +
                "  } ]\n" +
                "}";
        System.out.println(JsonUtil.toBean(jsonString, Book.class));
    }
}