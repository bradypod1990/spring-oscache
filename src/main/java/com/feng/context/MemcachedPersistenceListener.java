package com.feng.context;

import java.io.Serializable;
import java.util.Set;

import net.spy.memcached.MemcachedClient;

import com.apex.web.util.TokenUtil;
import com.feng.context.MemcachedFactory;
import com.opensymphony.oscache.base.Config;
import com.opensymphony.oscache.base.persistence.CachePersistenceException;
import com.opensymphony.oscache.base.persistence.PersistenceListener;

public class MemcachedPersistenceListener implements PersistenceListener,
		Serializable {
	public static final String CACHE_INSTANCE_KEY = "cache.instance";
	public static final String CACHE_ADDRESS_KEY = "cache.address";
	protected static final String GROUP_PREFIX = "__groups__:";
	private static final long serialVersionUID = 51808332L;
	private String instanceName;

	public PersistenceListener configure(Config config) {
		this.instanceName = config.getProperty("cache.instance");
		return this;
	}

	private String encodeKey(String key) {
		String s = this.instanceName + ":" + key;
		return TokenUtil.digest(s);
	}

	private String encodeGroupKey(String groupName) {
		return encodeKey("__groups__:" + groupName);
	}

	private MemcachedClient getClient() {
	    return MemcachedFactory.getInstance().getMemcachedClient(); }

	public boolean isStored(String key) throws CachePersistenceException {
		try {
			return (retrieve(key) != null);
		} catch (Exception e) {
			throw new CachePersistenceException(e.getMessage());
		}
	}

	public boolean isGroupStored(String groupName)
			throws CachePersistenceException {
		try {
			return (getClient().get(encodeGroupKey(groupName)) != null);
		} catch (Exception e) {
			throw new CachePersistenceException(e.getMessage());
		}
	}

	public void clear() throws CachePersistenceException {
		getClient().flush();
	}

	public void remove(String key) throws CachePersistenceException {
		try {
			getClient().delete(encodeKey(key));
		} catch (Exception e) {
			throw new CachePersistenceException(e.getMessage());
		}
	}

	public void removeGroup(String groupName) throws CachePersistenceException {
		try {
			getClient().delete(encodeGroupKey(groupName));
		} catch (Exception e) {
			throw new CachePersistenceException(e.getMessage());
		}
	}

	public Object retrieve(String key) throws CachePersistenceException {
		try {
			return getClient().get(encodeKey(key));
		} catch (Exception e) {
			throw new CachePersistenceException(e.getMessage());
		}
	}

	public void store(String key, Object obj) throws CachePersistenceException {
		try {
			getClient().set(encodeKey(key), 0, obj);
		} catch (Exception e) {
			throw new CachePersistenceException(e.getMessage());
		}
	}

	public void storeGroup(String groupName, Set group)
			throws CachePersistenceException {
		try {
			getClient().set(encodeGroupKey(groupName), 0, group);
		} catch (Exception e) {
			throw new CachePersistenceException(e.getMessage());
		}
	}

	public Set retrieveGroup(String groupName) throws CachePersistenceException {
		try {
			return ((Set) getClient().get(encodeGroupKey(groupName)));
		} catch (Exception e) {
			throw new CachePersistenceException(e.getMessage());
		}
	}

}
