package com.example.demo.web;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class IndexController {
    private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/index")
    public String index() {
        LOG.info("indexxxxxxxxxx");
        return "/index";
    }

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUser", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @Cacheable(cacheNames = {"user","user"}, key="#username") //缓存查询数据
    public User getUser(HttpServletRequest request, @Param("username") String username) {
        LOG.info("查询数据库");
        User user = userService.getUserByUsername(username);
        if(user == null){
            return null;
        }
        LOG.info(user.getUsername());
        return user;
    }

    @RequestMapping(value = "/updateUsername", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @CachePut(cacheNames = {"user","user"}, key="#username") //更新缓存数据
    public void updateName(@Param("username") String username){

    }

    @RequestMapping(value = "/delUser", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @CacheEvict(cacheNames = {"user","user"}, key="#username") //删除redis中的数据
    public String deleteUser(HttpServletRequest request, @Param("username") String username) {
        LOG.info("修改数据库");
        userService.delUserByUsername(username);
        return "delete";
    }

    @GetMapping("/test")
    // http://localhost:8998/hello?type=214    ModelAndView 返回页面和数据
    public ModelAndView getIndex(@RequestParam(value = "type",required = false) String type){
        //返回一个模型视图对象
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg",type);
        mv.setViewName("index");//返回页面为index
        return mv;
    }
}
