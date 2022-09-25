package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @description:
 * @author: Netter
 * @date: 2022-09-16 15:45
 */
@Service
//@Scope("prototype")
public class AplhaService {

    @Autowired
    private AlphaDao alphaDao;

    public AplhaService(){
        System.out.println("实例化AplhaService");
    }

    @PostConstruct
    public void init(){
        System.out.println("初始化AplhaService");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("销毁AplhaService");
    }

    public String find(){
        return alphaDao.select();
    }

}
