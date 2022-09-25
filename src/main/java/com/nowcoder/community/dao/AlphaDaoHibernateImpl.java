package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: Netter
 * @date: 2022-09-16 15:23
 */
@Repository("alphaDaoHibernate")
public class AlphaDaoHibernateImpl implements AlphaDao{

    @Override
    public String select() {
        return "Hibernate";
    }
}
