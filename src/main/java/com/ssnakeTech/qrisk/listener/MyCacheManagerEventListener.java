package com.ssnakeTech.qrisk.listener;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Status;
import net.sf.ehcache.event.CacheManagerEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by leo on 2017/2/24.
 */
public class MyCacheManagerEventListener implements CacheManagerEventListener{
    private final CacheManager cacheManager;
    Logger logger= LoggerFactory.getLogger(MyCacheManagerEventListener.class);
    public MyCacheManagerEventListener(CacheManager cacheManager){
        this.cacheManager=cacheManager;
    }
    @Override
    public void init() throws CacheException {
        logger.info("EhCache init");
    }

    @Override
    public Status getStatus() {
        logger.info("EhCache getStatus");
        return null;
    }

    @Override
    public void dispose() throws CacheException {
        logger.info("EhCache dispose");
    }

    @Override
    public void notifyCacheAdded(String cacheName) {
        logger.info("EhCache cache added "+cacheName);
        logger.info(cacheManager.getCache(cacheName).toString());
    }

    @Override
    public void notifyCacheRemoved(String cacheName) {
        logger.info("EhCache cache removed "+cacheName);
    }
}
