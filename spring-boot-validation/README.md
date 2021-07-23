# springboot集成validation

## 配置依赖
```
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-validation'
    implementation 'org.projectlombok:lombok:1.18.8'
    annotationProcessor 'org.projectlombok:lombok:1.18.8'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```

## 接口声明校验
```
@RequestMapping("user")
@RestController
public class UserController {

    @RequestMapping(method = RequestMethod.POST)
    public JsonResult<Object> postUser(@RequestBody @Valid User user) {
        return JsonResult.get("0", "操作成功", user);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public JsonResult<Object> putUser(@RequestBody @Validated(Update.class) User user) {
        return JsonResult.get("0", "操作成功", user);
    }
}
```

## 全局异常处理返回错误信息
```
@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 请求参数校验
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonResult<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error(e.getMessage(), e);
        String mess = e.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + error.getDefaultMessage())
                .collect(Collectors.joining("，"));
        return new JsonResult<>("1", mess, null);
    }
}
```
## 自定义校验注解
```
@Documented
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckCaseValidator.class)
public @interface CheckCase {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    CaseMode value();

}

public class CheckCaseValidator implements ConstraintValidator<CheckCase, String> {
    private CaseMode caseMode;

    @Override
    public void initialize(CheckCase checkCase) {
        this.caseMode = checkCase.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;

        if (caseMode == CaseMode.UPPER) return value.equals(value.toUpperCase());
        return value.equals(value.toLowerCase());
    }
}
```
## 支持分组校验
```
@NotNull(groups = Update.class)
private Long id;

public JsonResult<Object> putUser(@RequestBody @Validated(Update.class) User user) {
```