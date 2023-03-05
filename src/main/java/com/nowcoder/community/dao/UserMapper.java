package com.nowcoder.community.dao;

import com.nowcoder.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author: Netter
 * @date: 2022-09-24 15:50
 */
@Mapper  // @Repository也可以，习惯用Mapper
// 方法数量按需提供，一个方法对应一个sql
public interface UserMapper {

    User selectById(int id);

    User selectByName(String username);

    int selectByEmail(String email);

    int insertUser(User user);

    int updateStatus(int id, int status);

    int updateHeader(int id, String headerUrl);

    int updatePassword(int id, String password);

}
