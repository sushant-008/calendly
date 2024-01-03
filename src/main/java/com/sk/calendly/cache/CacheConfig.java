/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sk.calendly.cache;

import java.util.concurrent.TimeUnit;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Sushant
 */

@Configuration
public class CacheConfig {

    @Bean
    public Caffeine caffeineConfig() {
        return Caffeine.newBuilder().maximumSize(1000).expireAfterWrite(60, TimeUnit.MINUTES);
    }

    @Bean
    public CacheManager cacheManager(Caffeine caffeine) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }
}
