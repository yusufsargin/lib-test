package com.wkrzywiec.spring.library.service;

import com.wkrzywiec.spring.library.dao.UserDAO;
import com.wkrzywiec.spring.library.dao.UserDAOImpl;
import com.wkrzywiec.spring.library.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class UserServiceImplTest {

    PasswordEncoder passwordEncoder;

    @Mock
    UserService userService;

    @Mock
    UserDAO userDAO;

    User testUser;

    @Before
    public void setUp() throws Exception {
        passwordEncoder = new BCryptPasswordEncoder();
        this.userService = Mockito.mock(UserService.class);
        this.userDAO = Mockito.mock(UserDAOImpl.class);
        this.testUser = new User("ysargin", "123", "ysargin@gmail.com", true, "yusuf", "sargin");
        this.prepareMockState();
    }

    private void prepareMockState() {
        Mockito.when(this.userDAO.getActiveUserByEmail(this.testUser.getEmail())).thenReturn(this.testUser);

        Mockito.when(this.userService.isEmailAlreadyInUse(this.testUser.getEmail())).thenReturn(true);

        Mockito.when(this.userService.getUserByEmail(this.testUser.getEmail())).thenReturn(this.testUser);
    }

    @Test
    public void isPasswordEncoderWorkingCorrectly() {
        String password = "12346789";
        String encryptedPass = passwordEncoder.encode(password);

        assertNotEquals("Password should not equal to encryptes password", password, encryptedPass);
        assertTrue("Password should match for validation", passwordEncoder.matches(password, encryptedPass));
    }

    @Test
    public void isEmailAlreadyInUse() {
        User userForNotHaveControl = new User("Ali", "123", "sss@gmail.com", true, "Ali", "falan");
        Mockito.when(this.userDAO.getActiveUserByEmail(this.testUser.getEmail())).thenReturn(this.testUser);

        Mockito.when(this.userService.isEmailAlreadyInUse(this.testUser.getEmail())).thenReturn(true);

        boolean result = this.userService.isEmailAlreadyInUse(this.testUser.getEmail());
        boolean notHaveResult = this.userService.isEmailAlreadyInUse(userForNotHaveControl.getEmail());

        assertTrue("User should be the component", result);
        assertFalse("Template User should not be the component", notHaveResult);
    }

    @Test
    public void getUserByEmail() {
        User result = userService.getUserByEmail(this.testUser.getEmail());

        assertEquals("User should have", this.testUser, result);
    }
}