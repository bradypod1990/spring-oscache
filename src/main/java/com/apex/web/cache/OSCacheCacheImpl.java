package com.apex.web.cache;


import com.opensymphony.oscache.base.Cache;
import com.opensymphony.oscache.base.NeedsRefreshException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.support.SimpleValueWrapper;

/**
 * @author : xpk
 * @since : 13-11-5 上午10:06
 */
public class OSCacheCacheImpl implements org.springframework.cache.Cache {

    private String id;
    private Cache osCache;
    private String group;
    private int refreshPeriod = -1;
    private static final Logger logger = LoggerFactory
            .getLogger(OSCacheCacheImpl.class);
    private String cron;

    private String[] groups;

    public OSCacheCacheImpl(String group, String id, Cache osCache, String cron) {
        this.group = group;
        this.id = id;
        this.osCache = osCache;
        this.cron = cron;
        groups = new String[]{group};
    }

    /**
     * 毫秒构造缓存方法
     * @param group
     * @param id
     * @param osCache
     * @param refreshPeriod
     */
    public OSCacheCacheImpl ( String group, String id, Cache osCache, int refreshPeriod ) {
        this.group = group;
        this.id = id;
        this.osCache = osCache;
        this.refreshPeriod = refreshPeriod;
        groups = new String[]{group};
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public int getRefreshPeriod() {
        return refreshPeriod;
    }

    public void setRefreshPeriod(int refreshPeriod) {
        this.refreshPeriod = refreshPeriod;
    }

    @Override
    public ValueWrapper get(Object keyParam) {
        String key = getKey(keyParam);
        boolean hit = false;
        try {
            Cache cache = getCache();
            Object value = null;
            if ( getRefreshPeriod() != -1 ) {
                value = cache.getFromCache(key, refreshPeriod);
            } else {
                value = cache.getFromCache(key, -1, cron);
            }
            hit = value != null;
            return (value != null ? new SimpleValueWrapper(value) : null);
        } catch (NeedsRefreshException e) {
            getCache().cancelUpdate(key);
        } catch (Exception ex) {
            logger.warn(ex.getMessage());
        } finally {
            logger.debug("cache status: key={},hit={}", keyParam, key, hit);
        }
        return null;
    }

    private String getKey(Object keyParam) {
        return keyParam + "/" + id + "@" + group;
    }


    @Override
    public void put(Object key, Object Val) {
        try {
            getCache().putInCache(getKey(key), Val, groups);
        } catch (Exception ex) {
            logger.warn(ex.getMessage());
        }
    }

    private Cache getCache() {
        return getNativeCache();
    }

    @Override
    public void clear() {
        osCache.flushGroup(group);
    }

    @Override
    public void evict(Object key) {
        osCache.removeEntry(getKey(key));
    }

    @Override
    public String getName() {
        return id + "@" + group;
    }

    @Override
    public Cache getNativeCache() {
        return osCache;
    }

	@Override
	public <T> T get(Object key, Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}

}
