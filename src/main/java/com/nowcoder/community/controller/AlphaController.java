package com.nowcoder.community.controller;

import com.nowcoder.community.service.AplhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: Netter
 * @date: 2022-09-15 21:09
 */
@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AplhaService aplhaService;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "Hello.";
    }

    //5. 思考为什么返回的是Mybatis？
    //答：AplhaService 中的 AlphaDao 采用自动注入，两个AlphaDao***Impl中，Mybatis拥有@Primary 注解，因为注入该bean及其方法
    //http://localhost:8080/community/alpha/data
    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return aplhaService.find();
    }

    //6. 获取http对象实现请求响应（底层实现）
    // http://localhost:8080/community/alpha/http?code=123&name=abc
    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        //获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ": " + value);
        }
        System.out.println(request.getParameter("code"));

        //返回响应数据
        response.setContentType("text/html;charset=utf-8");
        try(
                PrintWriter writer = response.getWriter()
        ) {
            writer.write("<h1>牛客网</h1>");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    //7.请求处理，Spring Boot简化使用方式
    //  GET请求

    //方式一
    // http://localhost:8080/community/alpha/students?current=1&limit=20
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit){
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    //方式二
    // http://localhost:8080/community/alpha/student/123
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){
        System.out.println(id);
        return "a student";
    }

    //  POST请求
    // http://localhost:8080/community/alpha/student
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }


    //7.返回响应   --- HTML数据

    //方式一：
    // http://localhost:8080/community/alpha/teacher
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("name", "张三");
        mav.addObject("age", 30);
        mav.setViewName("/demo/view");
        return mav;
    }

    //方式二：返回String 为地址
    // http://localhost:8080/community/alpha/teacher2
    @RequestMapping(path = "/teacher2", method = RequestMethod.GET)
    public String getTeacher2(Model model){
        model.addAttribute("name", "李四");
        model.addAttribute("age", 40);
        return "/demo/view";
    }

    //8.返回响应   --- JSON数据（异步请求）
    //Java对象  -> JSON字符串  ->  JS对象
    // http://localhost:8080/community/alpha/emp
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp(){
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 23);
        emp.put("salary", 800.00);
        return emp;
    }


}
