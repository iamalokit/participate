package com.alokit.participate.core.cache.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.redis.core.RedisTemplate;

import com.alokit.participate.core.cache.service.RedisService;

public class RedisServiceImpl implements RedisService {
	
	private RedisTemplate<String, Object> redisTemplate;
	@Override
	public void set(String key, Object value, long time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void set(String key, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object get(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean del(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long del(List<String> keys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean expire(String key, long time) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getExpire(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean hasKey(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long incr(String key, long delta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long decr(String key, long delta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object hGet(String key, String hashKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean hSet(String key, String hashKey, Object value, long time) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void hSet(String key, String hashKey, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<Object, Object> hGetAll(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean hSetAll(String key, Map<String, Object> map, long time) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void hSetAll(String key, Map<String, ?> map) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hDel(String key, Object... hashKey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean hHasKey(String key, String hashKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long hIncr(String key, String hashKey, Long delta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long hDecr(String key, String hashKey, Long delta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Object> sMembers(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long sAdd(String key, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long sAdd(String key, long time, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean sIsMember(String key, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long sSize(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long sRemove(String key, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> lRange(String key, long start, long end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long lSize(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object lIndex(String key, long index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long lPush(String key, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long lPush(String key, Object value, long time) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long lPushAll(String key, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long lPushAll(String key, Long time, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long lRemove(String key, long count, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

}
