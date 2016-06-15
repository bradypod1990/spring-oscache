package com.feng.context;

import java.io.IOException;

import com.apex.web.cache.MemcachedConnectionFactory;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;

public class MemcachedFactory {
	
	private MemcachedClient memcachedClient;
	private static MemcachedFactory instance = new MemcachedFactory();
	private MemcachedFactory() {
		build();
	}
	
	private void build() {
		String server = "127.0.0.1:11211";
		try {
			memcachedClient = new MemcachedClient(new MemcachedConnectionFactory(), AddrUtil.getAddresses(server));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static MemcachedFactory getInstance() {
		return instance;
	}

	public MemcachedClient getMemcachedClient() {
		return memcachedClient;
	}

	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}
}


