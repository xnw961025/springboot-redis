package com.service.impl;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public boolean expire(String key, long time) {
		try {
			if (time > 0) {
				redisTemplate.expire(key, time, TimeUnit.SECONDS);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public long getExpire(String key) {
		return redisTemplate.getExpire(key);
	}

	@Override
	public boolean hasKey(String key) {
		try {
			return redisTemplate.hasKey(key);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void set(String key, Object value) {
		ValueOperations<String, Object> vo = redisTemplate.opsForValue();
		vo.set(key, value);
	}

	@Override
	public Object get(String key) {
		ValueOperations<String, Object> vo = redisTemplate.opsForValue();
		return vo.get(key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void del(String... key) {
		if(null != key && key.length > 0){
			if(key.length == 1) {
				redisTemplate.delete(key[0]);
			}else {
				redisTemplate.delete(CollectionUtils.arrayToList(key));
			}
		}
	}

}
