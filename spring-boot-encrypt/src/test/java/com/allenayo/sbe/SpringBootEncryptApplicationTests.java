package com.allenayo.sbe;

import com.allenayo.sbe.config.GlobalConfig;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootEncryptApplicationTests {

    @Autowired
    private GlobalConfig globalConfig;
    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void testGetValueFromConfig() {
        System.out.println(globalConfig.getProjectName());
        System.out.println(globalConfig.getAuthor());
    }

    @Test
    public void testEncryptString() {
        System.out.println(stringEncryptor.encrypt("allenayo"));
    }

    @Test
    public void testDecryptString() {
        System.out.println(stringEncryptor.decrypt("QH0DgNSGmV4y62rlIiKDY3XO31L8sCGA"));
    }
}