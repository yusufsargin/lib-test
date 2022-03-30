package com.wkrzywiec.spring.library.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {EmailServiceImplTest.class,
                LibraryServiceImplTest.class,
                UserServiceImplTest.class}
)
public class ServiceTestSuits {
}
