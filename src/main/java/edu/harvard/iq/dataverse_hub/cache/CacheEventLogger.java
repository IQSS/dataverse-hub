package edu.harvard.iq.dataverse_hub.cache;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.harvard.iq.dataverse_hub.controller.scheduled.InstallationGitImporter;

public class CacheEventLogger implements CacheEventListener<Object, Object> {

    Logger logger = LoggerFactory.getLogger(InstallationGitImporter.class);

    @Override
    public void onEvent(
      CacheEvent<? extends Object, ? extends Object> cacheEvent) {
        logger.info(cacheEvent.getType().toString(),
          cacheEvent.getKey(), cacheEvent.getOldValue(), cacheEvent.getNewValue());
    }
}