package com.ycyoes.test.serializable;

import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.decorators.FifoCache;
import org.apache.ibatis.mapping.MappedStatement;

public class TestCache {
	public static void main(String[] args) {
		MappedStatement ms = null;
		Cache cache = ms.getCache();
		FifoCache fifoCache = new FifoCache(cache);
		fifoCache.putObject("hello", "val");
		Object object = fifoCache.getObject("hello");
		System.out.println(object);
	}
}
