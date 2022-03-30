package com.wkrzywiec.spring.library.service;

import com.wkrzywiec.spring.library.config.LibraryTestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class UserServiceImplTest {

    PasswordEncoder passwordEncoder;

    @Before
    public void setUp() throws Exception {
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Test
    public void isPasswordEncoderWorkingCorrectly() {
        String password = "12346789";
        String encryptedPass = passwordEncoder.encode(password);

        assertNotEquals("Password should not equal to encryptes password", password, encryptedPass);
        assertTrue("Password should match for validation", passwordEncoder.matches(password, encryptedPass));
    }
}