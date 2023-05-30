package com.example.demo;

import com.example.demo.redis.domains.SKU;
import com.example.demo.redis.repositories.SKUCacheRepository;
import com.redis.om.spring.annotations.EnableRedisDocumentRepositories;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableRedisDocumentRepositories(basePackages = "com.example.demo.redis.*")
public class DemoApplication {

	@Autowired
	SKUCacheRepository skuCacheRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {

			List<SKU> cache1 = skuCacheRepository.findAllBySkuNameIn(new HashSet<>(Arrays.asList("SKU 1","SKU 2")));
			List<SKU> cache2 = skuCacheRepository.findAllBySkuNumberIn(new HashSet<>(Arrays.asList("A11111","A00000")));
			System.out.println("Searchable:");
			System.out.println(cache1.toString());
			System.out.println("Indexed:");
			System.out.println(cache2.toString());

		};
	}

}
