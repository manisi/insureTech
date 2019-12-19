package ir.insurance.startup.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(ir.insurance.startup.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(ir.insurance.startup.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.Ashkhas.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.Pooshesh.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.Khodro.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.Khodro.class.getName() + ".tips", jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.TipKhodro.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.Nerkh.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.Nerkh.class.getName() + ".nerkhs", jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.Nerkh.class.getName() + ".sherkatBimes", jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.SherkatBime.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.SherkatBime.class.getName() + ".names", jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.City.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.Country.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.MohasebeSales.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.MohasebeBadane.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.GrouhKhodro.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.Kohnegi.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.Sabeghe.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.NoeSabeghe.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.AdamKhesarat.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.JarimeDirkard.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.KhesaratSales.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.KohnegiBadane.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.KhesaratSrneshin.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.AdamKhesaratSarneshin.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.AdamKhesaratBadane.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.AnvaeKhodro.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.SalesJaniCalc.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.SalesSarneshinCalc.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.SalesMazadCalc.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.MoredEstefadeSales.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.OnvanKhodro.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.GroupOperationsData.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.SaalSakht.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.EstelaamSalesNerkh.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.VaziatBime.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.ModateBimename.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.SabegheKhesarat.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.TakhfifTavafoghi.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.KhesaratSalesMali.class.getName(), jcacheConfiguration);
            cm.createCache(ir.insurance.startup.domain.AdamKhesaratSalesMali.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
