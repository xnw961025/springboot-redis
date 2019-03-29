package com.service;

public interface RedisService {
	/**
	 * 指定缓存失效时间
	 * @param key
	 * @param time
	 * @return
	 */
	public boolean expire(String key,long time);
	
	/**
	 * 根据key获取过期时间
	 * key键不能为null
	 * @param key
	 * @return 时间（秒）返回0代表永久有效
	 */
	public long getExpire(String key);
	
	/**
	 * 判断key是不是存在
	 * @param key
	 * @return
	 */
	public boolean hasKey(String key);
	
	/**
	 * 普通缓存存放
	 * @param key
	 * @param value
	 */
	public void set(String key,Object value);
	/**
	 * 普通缓存获取
	 * @param key
	 * @return
	 */
	public Object get(String key);
	/**
	 * 删除缓存
	 * @param key
	 */
	public void del(String ... key);
}
