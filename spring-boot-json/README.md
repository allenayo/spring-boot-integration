# SpringBoot集成JSON

## 配置关键依赖
```
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
}
```

## 新建JSON转换工具类
```
public class JsonUtil {
    
    public static String toJson(Object o) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(o);
    }

    public static <T> T toBean(String xmlString, Class<T> clazz) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(xmlString, clazz);
    }
}
```

## 测试HTTP请求与响应中JSON数据自动转换
```
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
```