package cn.daxiaobiao.redis;


import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:daxiaobiao-redis-service.xml" })
@ActiveProfiles("dev")
public class BaseTest {
	/**
	 * Base test to define annotations
	 * **/
}
