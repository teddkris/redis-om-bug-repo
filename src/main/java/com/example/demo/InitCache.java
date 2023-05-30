package com.example.demo;

import com.example.demo.redis.domains.SKU;
import com.example.demo.redis.repositories.SKUCacheRepository;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InitCache {

  private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
  SKUCacheRepository skuCacheRepository;

  @Autowired
  public InitCache(SKUCacheRepository skuCacheRepository) {
    this.skuCacheRepository = skuCacheRepository;
  }

  @EventListener(ContextRefreshedEvent.class)
  @Transactional
  public void init() {
    cacheSKU();
  }

  private void cacheSKU() {
    LOGGER.info("Caching SKUs..");
    List<SKU> skuCaches = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      skuCaches.add(
          new SKU(Long.valueOf(i), "A" + i + i + i + i + i, "SKU " + i));
    }
    skuCacheRepository.saveAll(skuCaches);
  }
}