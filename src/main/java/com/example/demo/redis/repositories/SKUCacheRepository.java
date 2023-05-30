package com.example.demo.redis.repositories;

import com.example.demo.redis.domains.SKU;
import com.redis.om.spring.repository.RedisDocumentRepository;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Repository;

@Repository
public interface SKUCacheRepository extends RedisDocumentRepository<SKU, String> {

  Optional<SKU> findOneBySkuNumber(String skuNumber);

  List<SKU> findAllBySkuNumberIn(Set<String> skuNumbers);

  List<SKU> findAllBySkuNameIn(Set<String> dearSkuNumbers);

}
