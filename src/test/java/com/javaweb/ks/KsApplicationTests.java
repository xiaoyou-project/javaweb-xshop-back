package com.javaweb.ks;

import com.javaweb.ks.dao.UserDao;
import com.javaweb.ks.util.TokenVerify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;


@SpringBootTest
class KsApplicationTests {

	@Autowired
	UserDao userDao;

	@Autowired
	TokenVerify tokenVerify;

	@Test
	void contextLoads() {
		System.out.println( UUID.randomUUID().toString().replace("-", "").toLowerCase());
	}
}
