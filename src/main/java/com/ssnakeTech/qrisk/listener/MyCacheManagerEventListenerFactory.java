package com.ssnakeTech.qrisk.listener;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.event.CacheManagerEventListener;
import net.sf.ehcache.event.CacheManagerEventListenerFactory;

import java.util.Properties;

/**
 * Created by leo on 2017/2/24.
 */
public class MyCacheManagerEventListenerFactory extends CacheManagerEventListenerFactory {
    @Override
    public CacheManagerEventListener createCacheManagerEventListener(CacheManager cacheManager, Properties properties) {
        return new MyCacheManagerEventListener(cacheManager);
    }
}
