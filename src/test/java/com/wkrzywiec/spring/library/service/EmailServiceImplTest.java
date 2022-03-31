package com.wkrzywiec.spring.library.service;

import com.wkrzywiec.spring.library.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class EmailServiceImplTest {

    private EmailServiceImpl emailService;
    private User user;

    @Before
    public void setUp() throws Exception {
        this.emailService = new EmailServiceImpl();
        user = new User("ysargin", "123", "sss@gmail.com", true, "yusuf", "sargin");
    }

    @Test
    public void prepareUserRegistrationConfirmEmail() {
        String preparedTest = this.emailService.prepareUserRegistrationConfirmEmail(user);

        assertNotNull("email service should not null", this.emailService);
        assertNotNull("User should not null", this.user);
        assertTrue("Message should contain user name and hello keyword", preparedTest.contains("Hello " + this.user.getFirstName()));
        assertTrue("Message should contain register keyword", preparedTest.contains("Thank you for registering"));
    }

    @Test
    public void prepareForgotPasswordEmail() {
        String path = "/forgot";
        String token = "asdfgh";
        String preparedTest = this.emailService.prepareForgotPasswordEmail(this.user, path, token);

        assertNotNull("email service should not null", this.emailService);
        assertNotNull("User should not null", this.user);
        assertTrue("Message should contain user name and hello keyword", preparedTest.contains("Hello " + this.user.getFirstName()));
        assertTrue("Message should contain reset password keyword", preparedTest.contains("reseting password"));
        assertTrue("Message inculde token", preparedTest.contains("&token=" + token));
        assertTrue("Message inculde path", preparedTest.contains(path));
    }

    @Test
    public void generateResetPath() {
        String path = "/forgot";
        String token = "asdfgh";

        String generatePath = this.emailService.generateResetPath(user, path, token);

        assertTrue("Generated path include token", generatePath.contains("&token=" + token));
    }
}