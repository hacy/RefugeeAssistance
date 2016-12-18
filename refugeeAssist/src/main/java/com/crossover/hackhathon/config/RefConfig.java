package com.crossover.hackhathon.config;

import javax.servlet.Filter;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import com.crossover.hackhathon.RefugeeAssistConstants;

import net.sf.ehcache.config.CacheConfiguration;

@EnableAsync
@Configuration
@EnableCaching
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = { "com.crossover.hackhathon" })
public class RefConfig implements CachingConfigurer {	
	
	@Bean
	public Filter logFilter() {
	    CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
	    filter.setIncludeQueryString(true);
	    filter.setIncludePayload(false);
	    filter.setMaxPayloadLength(5120);	    
	    return filter;
	}
	
	
	@Bean	
	public BeanNameAutoProxyCreator beanNameAutoProxyCreator() {
		BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
		beanNameAutoProxyCreator.setBeanNames("*Repository"); 
		beanNameAutoProxyCreator.setInterceptorNames("repositorySecurityInterceptor");
		return beanNameAutoProxyCreator;
	}

    @Bean
    @Override
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCacheManager());
    }
    
	@Bean(destroyMethod = "shutdown")
	public net.sf.ehcache.CacheManager ehCacheManager() {
		net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
		config.addCache(cacheConfig(RefugeeAssistConstants.AUTH_TOKEN_CACHE, 1000));
		config.addCache(cacheConfig(RefugeeAssistConstants.CITY_CACHE, 500));
		config.addCache(cacheConfig(RefugeeAssistConstants.CITY_DISTRICT_CACHE, 500));
		return net.sf.ehcache.CacheManager.newInstance(config);
	}

	private CacheConfiguration cacheConfig(String name, long maxEntries) {
		CacheConfiguration cacheConfig = new CacheConfiguration();
		cacheConfig.setName(name);
		cacheConfig.setTimeToLiveSeconds(1800);
		cacheConfig.setOverflowToOffHeap(false);		
		cacheConfig.setMaxEntriesLocalHeap(maxEntries);
		cacheConfig.setMemoryStoreEvictionPolicy("LRU");
		return cacheConfig;
	}
	
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }

	@Override
	public CacheResolver cacheResolver() {
		return null;
	}

	@Override
	public CacheErrorHandler errorHandler() {
		return null;
	}	

}

