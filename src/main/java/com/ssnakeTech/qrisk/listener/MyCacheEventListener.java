package com.ssnakeTech.qrisk.listener;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by leo on 2017/2/24.
 */
public class MyCacheEventListener implements CacheEventListener {
    Logger logger= LoggerFactory.getLogger(MyCacheEventListener.class);
    @Override
    public void notifyElementRemoved(Ehcache ehcache, Element element) throws CacheException {
        StringBuilder strBuilder=new StringBuilder("EhCache element removed "+ehcache.getName()+" ");
        for(int i=0;i<ehcache.getKeys().size();i++){
            strBuilder.append(ehcache.getKeys().get(i)).append(" ");
        }
        logger.info(strBuilder.toString());

    }

    @Override
    public void notifyElementPut(Ehcache ehcache, Element element) throws CacheException {
        StringBuilder strBuilder=new StringBuilder("EhCache element put "+ehcache.getName()+" ");
        for(int i=0;i<ehcache.getKeys().size();i++){
            strBuilder.append(ehcache.getKeys().get(i)).append(" ");
        }
        logger.info(strBuilder.toString());
    }

    @Override
    public void notifyElementUpdated(Ehcache ehcache, Element element) throws CacheException {
        StringBuilder strBuilder=new StringBuilder("EhCache element updated "+ehcache.getName()+" ");
        for(int i=0;i<ehcache.getKeys().size();i++){
            strBuilder.append(ehcache.getKeys().get(i)).append(" ");
        }
        logger.info(strBuilder.toString());
    }

    @Override
    public void notifyElementExpired(Ehcache ehcache, Element element) {
        StringBuilder strBuilder=new StringBuilder("EhCache element expired "+ehcache.getName()+" ");
        for(int i=0;i<ehcache.getKeys().size();i++){
            strBuilder.append(ehcache.getKeys().get(i)).append(" ");
        }
        logger.info(strBuilder.toString());
    }

    @Override
    public void notifyElementEvicted(Ehcache ehcache, Element element) {
        StringBuilder strBuilder=new StringBuilder("EhCache element evicted "+ehcache.getName()+" ");
        for(int i=0;i<ehcache.getKeys().size();i++){
            strBuilder.append(ehcache.getKeys().get(i)).append(" ");
        }
        logger.info(strBuilder.toString());
    }

    @Override
    public void notifyRemoveAll(Ehcache ehcache) {
        StringBuilder strBuilder=new StringBuilder("EhCache element remove all "+ehcache.getName()+" ");
        for(int i=0;i<ehcache.getKeys().size();i++){
            strBuilder.append(ehcache.getKeys().get(i)).append(" ");
        }
        logger.info(strBuilder.toString());
    }

    @Override
    public void dispose() {
       logger.info("EhCache element dispose");
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        logger.info("EhCache element clone");
        return null;
    }
}
