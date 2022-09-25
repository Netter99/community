package com.nowcoder.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: Netter
 * @date: 2022-09-16 15:23
 */
@Repository
@Primary
public class AlphaDaoMybatisImpl implements AlphaDao{

    @Override
    public String select() {
        return "Mybatis";
    }
}
