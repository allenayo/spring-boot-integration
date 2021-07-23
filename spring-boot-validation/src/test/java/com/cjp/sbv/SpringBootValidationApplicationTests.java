package com.cjp.sbv;

import com.cjp.sbv.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@SpringBootTest
class SpringBootValidationApplicationTests {

    @Autowired
    private Validator validator;

    @Test
    void test() {
        User user = new User();
        user.setUsername("CJP");
        Set<ConstraintViolation<User>> validate = validator.validate(user);
        validate.stream().map(violation -> violation.getPropertyPath() + violation.getMessage()).forEach(System.out::println);
    }

}
