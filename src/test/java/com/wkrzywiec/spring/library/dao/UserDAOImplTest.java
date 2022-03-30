package com.wkrzywiec.spring.library.dao;

import com.wkrzywiec.spring.library.config.LibraryConfig;
import com.wkrzywiec.spring.library.entity.Role;
import com.wkrzywiec.spring.library.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LibraryConfig.class)
@WebAppConfiguration("src/main/java")
public class UserDAOImplTest {

    @Autowired
    private UserDAOImpl userDAO;

    private String mockUserName = "ysargin";
    private String noHaveUserName = "ttttt";

    @Test
    @Transactional
    public void getActiveUser() {
        User user = userDAO.getActiveUser(mockUserName);
        User noUser = userDAO.getActiveUser(noHaveUserName);
        System.out.println(user.getRoles());

        assertNotNull("User should not null", user);
        assertEquals("Username should be ysargin", user.getUsername(), mockUserName);
        assertTrue("user should enabled", user.isEnable());
    }
}