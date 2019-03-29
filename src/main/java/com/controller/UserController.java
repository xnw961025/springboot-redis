package com.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bean.User;
import com.service.RedisService;
import com.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RedisService redisService;
	
	@RequestMapping("/selectOne/{id}")
	public User selectOne(@PathVariable("id")String id){
		User user = userService.selectOne(Long.valueOf(id));
		redisService.set(String.valueOf(user.getId()), user);
		redisService.expire(String.valueOf(user.getId()), 60);
		return user;
	}
	//  通过key获取value
    @RequestMapping("/getgoodsfromredis")
    public User getRedis(@RequestParam String key) {
        return (User) redisService.get(key);
    }

    //  根据key获取缓存过期时间
    @RequestMapping("/getTime")
    public long getExpire(@RequestParam String key) {
        return redisService.getExpire(key);
    }

    //  根据key删除缓存
    @RequestMapping("/delRediskey")
    public void del(@RequestParam String ... key) {
        redisService.del(key);
    }       
}
