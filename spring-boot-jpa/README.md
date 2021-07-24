# SpringBoot集成JPA&h2

## 配置关键依赖
```xml
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    runtimeOnly 'com.h2database:h2'
}
```

## h2配置
```xml
# h2配置
# 启用SQL语句的日志记录
spring.jpa.show-sql=true
# 设置ddl模式
spring.jpa.hibernate.ddl-auto=update
## 数据库连接设置
spring.datasource.url=jdbc:h2:mem:test
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driverClassName=org.h2.Driver
## 数据初始化设置
#spring.sql.init.schema-locations=classpath*:db/schema.sql
#spring.sql.init.data-locations=classpath*:db/data.sql
## h2 web console设置
# 表明使用的数据库平台是h2
spring.sql.init.platform=h2
# 进行该配置后，h2 web console就可以在远程访问了，否则只能在本机访问。
spring.h2.console.settings.web-allow-others=true
# 进行该配置后，你就可以通过项目名称/h2访问h2 web console。
spring.h2.console.path=/h2
# 进行该配置后，程序开启时就会启动h2 web console，当然这是默认的，如果你不想在启动程序时启动h2 web console，那么就设置为false。
spring.h2.console.enabled=true
```

## 实体类
```java
/**
 * 与Book是一对多的关系
 * 与Employee是一对一的关系
 * 与School是多对多的关系
 */
@Entity
@Table(name = "t_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonManagedReference // 反序列化时，注入@JsonBackReference标注的属性
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE) // user对应Book字段user，实现级联删除
    private List<Book> books;

    @JsonManagedReference
    @OneToOne(mappedBy = "user")
    private Employee employee;

    @ManyToMany(mappedBy = "users")
    private List<School> schools;
}


/**
 * 与User是多对一关系
 */
@Entity
@Table(name = "t_book")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonBackReference // 序列化时如果有值会被忽略，即结果不含该字段，防止双向引用导致序列化时无限递归
    @ManyToOne
    @JoinColumn(name = "user_id") // 可以省略，默认为：字段名_id
    private User user;
}


/**
 * 与User是一对一关系
 */
@Entity
@Table(name = "t_employee")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;

    @JsonBackReference // 序列化时如果有值会被忽略，即结果不含该字段，防止双向引用导致序列化时无限递归
    @OneToOne
    @JoinColumn(name = "user_id") // 可以省略，默认为：字段名_id
    private User user;
}


/**
 * 与User是多对多关系
 */
@Entity
@Table(name = "t_school")
@Data
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonBackReference // 查询时不会关联中间表，序列化时如果有值会被忽略，即结果不含该字段，防止双向引用导致序列化时无限递归
    @ManyToMany
    @JoinTable(name = "t_user_school", joinColumns = {@JoinColumn(name = "school_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<User> users;
}
```
## Dao
```java
public interface BookRepository extends JpaRepository<Book, Long> {

}
```
## Service
```java
@Service
public class UserService {

    private final UserRepository userDao;

    public UserService(UserRepository userDao) {
        this.userDao = userDao;
    }

    public void save(User user) {
        userDao.save(user);
    }

    public void update(User user) {
        userDao.save(user);
    }

    public User findById(long id) {
        return userDao.findById(id).orElse(null);
    }

    public void deleteById(long id) {
        userDao.deleteById(id);
    }
}
```
## Controller
```java
@RequestMapping("user")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public JsonResult postUser(@RequestBody User user) {
        userService.save(user);
        return JsonResult.get("0", "操作成功");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public JsonResult putUser(@PathVariable("id") long id, @RequestBody User user) {
        User getUser = userService.findById(id);
        if (getUser == null) return JsonResult.get("1", "数据不存在");

        user.setId(getUser.getId());
        userService.update(user);
        return JsonResult.get("0", "操作成功");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JsonResult getUser(@PathVariable("id") long id) {
        return JsonResult.get("0", "操作成功", userService.findById(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public JsonResult deleteUser(@PathVariable("id") long id) {
        User getUser = userService.findById(id);
        if (getUser == null) return JsonResult.get("1", "数据不存在");

        userService.deleteById(getUser.getId());
        return JsonResult.get("0", "操作成功");
    }
}
```