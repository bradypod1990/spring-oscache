package com.feng.context;

import java.io.Serializable;
import java.util.Set;

import net.spy.memcached.MemcachedClient;

import com.apex.web.util.TokenUtil;
import com.feng.context.MemcachedFactory;
import com.opensymphony.oscache.base.Config;
import com.opensymphony.oscache.base.persistence.CachePersistenceException;
import com.opensymphony.oscache.base.persistence.PersistenceListener;

public class MemcachedPersistenceListener2 implements PersistenceListener,
		Serializable {

	private static final long serialVersionUID = 935398534972929740L;

	private MemcachedClient getClient() {
		return MemcachedFactory.getInstance().getMemcachedClient();
	}
	
	@Override
	public void clear() throws CachePersistenceException {
		getClient().flush();
	}

	@Override
	public PersistenceListener configure(Config arg0) {
		return this;
	}

	@Override
	public boolean isGroupStored(String arg0) throws CachePersistenceException {
		return retrieveGroup(arg0)!=null;
	}

	@Override
	public boolean isStored(String arg0) throws CachePersistenceException {
		return retrieve(arg0)!=null;
	}

	@Override
	public void remove(String arg0) throws CachePersistenceException {
		getClient().delete(arg0);
	}

	@Override
	public void removeGroup(String arg0) throws CachePersistenceException {
		getClient().delete(arg0);
	}

	@Override
	public Object retrieve(String arg0) throws CachePersistenceException {
		return getClient().get(arg0);
	}

	@Override
	public Set retrieveGroup(String arg0) throws CachePersistenceException {
		return (Set) getClient().get(arg0);
	}

	@Override
	public void store(String arg0, Object arg1)
			throws CachePersistenceException {
		getClient().set(arg0, 0, arg1);
	}

	@Override
	public void storeGroup(String arg0, Set arg1)
			throws CachePersistenceException {
		getClient().set(arg0, 0, arg1);
		
	}
	

}
