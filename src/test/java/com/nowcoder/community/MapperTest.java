package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.LoginTicketMapper;
import com.nowcoder.community.dao.MessageMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.LoginTicket;
import com.nowcoder.community.entity.Message;
import com.nowcoder.community.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: Netter
 * @date: 2022-09-24 16:15
 */
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTest {

    //9. Mybatis 使用
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DiscussPostMapper discussPostMapper;
    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Test
    public void testSelectUser(){
        User user = userMapper.selectById(101);
        System.out.println(user);

        user = userMapper.selectByName("liubei");
        System.out.println(user);

        int num = userMapper.selectByEmail("nowcoder101@sina.com");
        System.out.println(num);
    }

    @Test
    public void testInsertUser(){
        User user = new User();
        user.setUsername("test");
        user.setPassword("123");
        user.setSalt("abc");
        user.setEmail("test@qq.com");
        user.setHeaderUrl("https://www.baidu.com/");
        user.setCreateTime(new Date());
        user.setActivationCode("123");

        int rows = userMapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());
    }

    @Test
    public void testUpdateUser(){
        int rows = userMapper.updateStatus(150, 1);
        System.out.println(rows);

        rows = userMapper.updateHeader(150, "https://www.nowcoder.com/study/live/246/1/23");
        System.out.println(rows);

        rows = userMapper.updatePassword(150, "1113");
        System.out.println(rows);
    }


    //10. 社区开发
    @Test
    public void testDiscussPost(){
        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(149, 0, 10, 0);
        for (DiscussPost discussPost : list) {
            System.out.println(discussPost);
        }

        int rows = discussPostMapper.selectDiscussPostRows(149);
        System.out.println(rows);
    }


    //16.
    @Test
    public void testInsertLoginTicket() {
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(101);
        loginTicket.setTicket("abc");
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + 1000 * 60 * 10));
        loginTicketMapper.insertLoginTicket(loginTicket);
    }

    @Test
    public void testSelectLoginTicket() {
        LoginTicket loginTicket = loginTicketMapper.selectByTicket("abc");
        System.out.println(loginTicket);

        loginTicketMapper.updateStatus("abc", 1);
    }

    //22.增加帖子
    @Test
    public void testInsertDiscussPost() {
        DiscussPost discussPost = new DiscussPost();
        discussPost.setUserId(111);
        discussPost.setTitle("Test");
        discussPost.setContent("This is a insert test");
        discussPost.setType(0);
        discussPost.setStatus(0);
        discussPost.setCreateTime(new Date());
        discussPost.setCommentCount(12);
        discussPost.setScore(12);
        System.out.println(discussPostMapper.insertDiscussPost(discussPost));
    }

    //26.消息测试
    @Test
    public void testSelectLetters() {
        List<Message> messages = messageMapper.selectConversations(111, 0, 20);
        for (Message message : messages) {
            System.out.println(message);
        }

        int count = messageMapper.selectConversationCount(111);
        System.out.println(count);

        messages = messageMapper.selectLetter("111_112", 0, 10);
        for (Message message : messages) {
            System.out.println(message);
        }

        System.out.println(messageMapper.selectLetterCount("111_112"));

        System.out.println(messageMapper.selectLetterUnreadCount(131, "111_131"));
    }

}
