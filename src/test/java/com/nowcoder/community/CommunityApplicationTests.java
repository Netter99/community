package com.nowcoder.community;

import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.service.AplhaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	//1. class反射生成bean对象
	@Test
	public void testApplicationContext(){
		System.out.println(applicationContext);

		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
		System.out.println(alphaDao.select());

		alphaDao = applicationContext.getBean("alphaDaoHibernate", AlphaDao.class);
		System.out.println(alphaDao.select());

	}

	//2. 证明Spring Boot默认使用单例模式
	@Test
	public void testBeanManagement(){
		AplhaService aplhaService = applicationContext.getBean(AplhaService.class);
		System.out.println(aplhaService);

		aplhaService = applicationContext.getBean(AplhaService.class);
		System.out.println(aplhaService);
	}

	//3. 调用***Config 自定义bean
	@Test
	public void testBeanConfig(){
		SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}


	//4. Spring Boot便利化使用Bean
	@Autowired
	private AplhaService aplhaService;

	@Autowired
	@Qualifier("alphaDaoHibernate") //指定注入的实体类
	private AlphaDao alphaDao;

	@Test
	public void testDI(){
		System.out.println(aplhaService);
		System.out.println(alphaDao);
	}

	//5. 见AlphaController

}
