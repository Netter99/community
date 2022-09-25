package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: Netter
 * @date: 2022-09-25 10:06
 */
@Mapper
public interface DiscussPostMapper {

    // 动态sql
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    // @Param注解用于给参数取别名，
    // 如果只有一个参数，并且在<if>中使用，则必须加别名
    int selectDiscussPostRows(@Param("userId") int userId);

}
