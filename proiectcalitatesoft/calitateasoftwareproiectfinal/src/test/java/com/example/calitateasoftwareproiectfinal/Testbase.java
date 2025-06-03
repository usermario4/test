package com.example.calitateasoftwareproiectfinal;

import com.example.calitateasoftwareproiectfinal.testng.TestListeners;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Listeners;

@SpringBootTest
@Listeners(TestListeners.class)
public class Testbase {
}
