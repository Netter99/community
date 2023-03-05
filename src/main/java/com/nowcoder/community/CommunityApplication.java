package com.nowcoder.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CommunityApplication {

	//@PostConstruct
	//public void init() {
	//	//40.Spring整合ES
	//	//解决netty启动冲突问题  availableProcessors is already set to ...
	//	//System.setProperty("es.set.netty.runtime.available.processors", "false");
	//}

	public static void main(String[] args) {
		SpringApplication.run(CommunityApplication.class, args);
	}

}
