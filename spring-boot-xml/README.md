# SpringBoot集成XML

## 配置关键依赖
```
dependencies {
    implementation 'com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.12.3'
}
```

## 新建XML转换工具类
```
public class XmlUtil {

    public static String toXML(Object o) throws Exception {
        XmlMapper mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT); // 格式化
        return mapper.writeValueAsString(o);
    }

    public static <T> T toBean(String xmlString, Class<T> clazz) throws Exception {
        XmlMapper mapper = new XmlMapper();
        return mapper.readValue(xmlString, clazz);
    }
}
```

## 测试HTTP请求与响应中XML数据自动转换
```
@RequestMapping("book")
@RestController
public class BookController {

    @RequestMapping(value = "/getBook", method = RequestMethod.GET, produces = "application/xml;charset=UTF-8")
    public Book getBook() {
        List<Chapter> chapters = new ArrayList<>(Arrays.asList(
                new Chapter(1L, "chapter1", "chapter1", 8),
                new Chapter(2L, "chpater2", "chapter2", 8)));
        return new Book(1L, "book1", "allenAyo", "eng", 10.50, 50, chapters);
    }

    @RequestMapping(value = "postBook", method = RequestMethod.POST, consumes = "application/xml;charset=UTF-8")
    public Book postUser(@RequestBody Book book) {
        System.out.println(book);
        return book;
    }

    @RequestMapping(value = "postMessage", method = RequestMethod.POST, consumes = "application/xml;charset=UTF-8", produces = "application/xml;charset=UTF-8")
    public Message postMessage(@RequestBody Message message) {
        System.out.println(message);
        return message;
    }
}
```
