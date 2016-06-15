package com.apex.web.cache;

import com.apex.web.AppConfig;
import com.opensymphony.oscache.base.CacheEntry;
import com.opensymphony.oscache.base.events.CacheEntryEvent;
import com.opensymphony.oscache.base.events.CacheGroupEvent;
import com.opensymphony.oscache.base.events.CachePatternEvent;
import com.opensymphony.oscache.base.events.CachewideEvent;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;

import java.util.*;

/**
 * @author : xpk
 * @since : 13-11-5 上午9:40
 */
public class OsCacheCacheManager extends AbstractCacheManager implements com.opensymphony.oscache.base.events.CacheEntryEventListener {
    private static Logger logger = LoggerFactory.getLogger(OsCacheCacheManager.class);
    public static Map<String, Map<String, String>> cacheInfo = new HashMap<String, Map<String, String>>();
    private String oscacheProperties = "/oscache.properties";
    private String cacheDefineProperties = "/cache.properties";
    private GeneralCacheAdministrator cacheAdmin;

    public String getCacheDefineProperties() {
        return cacheDefineProperties;
    }

    public void setCacheDefineProperties(String cacheDefineProperties) {
        this.cacheDefineProperties = cacheDefineProperties;
    }

    public String getOscacheProperties() {
        return oscacheProperties;
    }

    public void setOscacheProperties(String oscacheProperties) {
        this.oscacheProperties = oscacheProperties;
    }

    @Override
    protected Collection<? extends Cache> loadCaches() {
        Properties ps = AppConfig.loadProperties(cacheDefineProperties);
        Collection<OSCacheCacheImpl> cache = new ArrayList<OSCacheCacheImpl>();
        if (ps != null) {
            // {cacheId}.name=
            // {cacheId}.group
            // {cacheId}.key
            // {cacheId}.cron=  or {cacheId}.period=

            //永不过期 T+24 T+12 T+3 T+1
            // {cacheId}.scope =application|session|request default application
            Set<Object> keys = ps.keySet();
            Set<String> names = new HashSet<String>();
            for (Object key : keys) {
                String keyStr = (String) key;
                if (keyStr.endsWith(".name")) {
                    names.add(keyStr.substring(0, keyStr.indexOf(".name")));
                }
            }
//            秒（0~59）
//            分钟（0~59）
//            小时（0~23）
//            天（月）（0~31，但是你需要考虑你月的天数）
//            月（0~11）
//            天（星期）（1~7 1=SUN 或 SUN，MON，TUE，WED，THU，FRI，SAT）
//            7.年份（1970－2099）

            for (String id : names) {
                String name = ps.getProperty(id + ".name");//
                if (name == null) continue;
                String group = ps.getProperty(id + ".group", "defalut");//
                String cron = ps.getProperty(id + ".cron", "0 0 0  * * ? *");//默认一天,一般采集完才处理
                String period = ps.getProperty(id + ".period", "T+24");//默认一天
                Map<String, String> info = new HashMap<String, String>();
                info.put("id", id);
                info.put("group", group);
                info.put("groupName", ps.getProperty("group." + group, "默认分组"));
                info.put("name", name);
                if (cron != null)
                    info.put("cron", cron);
                if (period != null)
                    info.put("period", period);
                cacheInfo.put(id, info);
                if (period != null) {
                    if (period.toUpperCase().startsWith("T+")) {
                        int hour = Integer.parseInt(period.substring("T+".length()));
                        //永不过期 T+24 T+12 T+3 T+1
                        if (hour == 24) hour = 23;
                        cron = "0 0 0/" + hour + "  * * ? *";
                        cache.add(new OSCacheCacheImpl(group, id, cacheAdmin.getCache(), cron));
                    } else if (!StringUtils.isEmpty(period) && StringUtils.isNumeric(period)) {
                        int time = Integer.parseInt(period);// 按毫秒配置
                        cache.add(new OSCacheCacheImpl(group, id, cacheAdmin.getCache(), time));
                    } else {
                        cache.add(new OSCacheCacheImpl(group, id, cacheAdmin.getCache(), cron));
                    }
                }
            }

            return cache;
        }
        return Collections.emptyList();
    }

    @Override
    public void afterPropertiesSet() {
        cacheInfo.clear();
        if (cacheAdmin == null) {
            Properties ps = AppConfig.loadProperties(oscacheProperties);
            this.cacheAdmin = ps == null ? new GeneralCacheAdministrator() : new GeneralCacheAdministrator(ps);
        }
        this.cacheAdmin.getCache().addCacheEventListener(this);
        super.afterPropertiesSet();
    }

    public GeneralCacheAdministrator getCacheAdmin() {
        return cacheAdmin;
    }

    public void setCacheAdmin(GeneralCacheAdministrator cacheAdmin) {
        this.cacheAdmin = cacheAdmin;
    }

    @Override
    public void cacheEntryAdded(CacheEntryEvent cacheEntryEvent) {
        if (logger.isDebugEnabled())
            logger.debug("cacheEntryAdded {}", cacheEntryEvent.getEntry().getKey());

    }

    @Override
    public void cacheEntryFlushed(CacheEntryEvent cacheEntryEvent) {
        if (logger.isDebugEnabled()) {
            CacheEntry entry = cacheEntryEvent == null ? null : cacheEntryEvent.getEntry();
            if (entry != null)
                logger.debug("cacheEntryFlushed {}", entry.getKey());
            else logger.debug("cacheEntryFlushed ");
        }
    }

    @Override
    public void cacheEntryRemoved(CacheEntryEvent cacheEntryEvent) {
        if (logger.isDebugEnabled()) {
            CacheEntry entry = cacheEntryEvent == null ? null : cacheEntryEvent.getEntry();
            if (entry != null)
                logger.debug("cacheEntryRemoved {}", entry.getKey());
            else logger.debug("cacheEntryRemoved ");
        }
    }

    @Override
    public void cacheEntryUpdated(CacheEntryEvent cacheEntryEvent) {
        if (logger.isDebugEnabled())
            logger.debug("cacheEntryUpdated {}", cacheEntryEvent.getEntry().getKey());
    }

    @Override
    public void cacheGroupFlushed(CacheGroupEvent cacheGroupEvent) {
        if (logger.isDebugEnabled())
            logger.debug("cacheGroupFlushed {}", cacheGroupEvent.getGroup());
    }

    @Override
    public void cachePatternFlushed(CachePatternEvent cachePatternEvent) {
        if (logger.isDebugEnabled())
            logger.debug("cachePatternFlushed {}", cachePatternEvent.getPattern());
    }

    @Override
    public void cacheFlushed(CachewideEvent cachewideEvent) {
        logger.debug("cache flushed ");
    }
}
