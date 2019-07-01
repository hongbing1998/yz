package org.edu.cdtu.yz.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.edu.cdtu.yz.Relam.ShiroRealm;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {


    /**
     * 注入Shiro Bean的生命周期管理器
     */
    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor injectLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 注入证书认证匹配器
     * 自定义配置加密方式
     */
    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher injectHashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");// 指定加密方式为MD5
        credentialsMatcher.setHashIterations(1024);// 加密次数
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    /**
     * 注入自定义的ShiroRealm
     */
    @Bean("shiroRealm")
    public ShiroRealm injectShiroRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher matcher) {
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCredentialsMatcher(matcher);
        return shiroRealm;
    }

    /**
     * 注入安全管理器
     * 管理subject及其相关的登陆验证，授权等，需配置Realm和缓存管理
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager injectDefaultWebSecurityManager(@Qualifier("hashedCredentialsMatcher")
                                                                             HashedCredentialsMatcher hashedCredentialsMatcher) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setCacheManager(injectCacheManager());// 配置缓存管理器
        securityManager.setRememberMeManager(rememberMeManager());
        securityManager.setRealm(injectShiroRealm(hashedCredentialsMatcher));// 配置Realm
        return securityManager;
    }

    /**
     * 注入AuthorizationAttributeSource通知器
     * 开启Shrio的AOP权限注解支持
     */
    @Bean("authorizationAttributeSourceAdvisor")
    public AuthorizationAttributeSourceAdvisor injectAuthorizationAttributeSourceAdvisor(@Qualifier("securityManager")
                                                                                                 DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 注入默认的Advistor(通知器)自动代理创建器
     * 需要先注入lifecycleBeanPostProcessor
     * 用来扫描上下文，寻找所有的Advistor，将这些Advistor应用到符合其定义的切入点(AOP)的Bean中
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 注入Shiro的过滤器
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean injectShirFilter(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);// 设置 SecurityManager
        shiroFilter.setSuccessUrl("/main");// 设置登录成功跳转Url
        shiroFilter.setLoginUrl("/login.html");// 设置登录跳转Url
        shiroFilter.setUnauthorizedUrl("/error/unAuth");// 设置未授权提示Url
        /*
         * anon：匿名用户可访问
         * authc：认证用户可访问
         * user：使用rememberMe可访问
         * perms：对应权限可访问
         * roles：对应角色权限可访问
         */
        Map<String, String> definitionMap = new LinkedHashMap<>();
        definitionMap.put("/**/*.js", "anon");
        definitionMap.put("/**/*.css", "anon");
        definitionMap.put("/**/*.png", "anon");
        definitionMap.put("/**/*.jpg", "anon");
        definitionMap.put("/**/*.gif", "anon");
        definitionMap.put("/**/*.bmp", "anon");
        definitionMap.put("/swagger*", "anon");
        definitionMap.put("/**/login.html", "anon");
        definitionMap.put("/druid/**", "anon");
        definitionMap.put("/static/**", "anon");
        definitionMap.put("/user/login", "anon");
        definitionMap.put("/*", "authc");
        definitionMap.put("/user/index", "authc");
        definitionMap.put("/vip/index", "roles[vip]");
        definitionMap.put("/logout", "logout");
        shiroFilter.setFilterChainDefinitionMap(definitionMap);
        return shiroFilter;


    }

    /**
     * 注入缓存管理器
     */
    @Bean("cacheManager")
    public EhCacheManager injectCacheManager() {
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return cacheManager;
    }

    @Bean
    public SimpleCookie rememberMeCookie() {
        //System.out.println("ShiroConfiguration.rememberMeCookie()");
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

    @Bean
    public CookieRememberMeManager rememberMeManager() {
        //System.out.println("ShiroConfiguration.rememberMeManager()");
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return cookieRememberMeManager;
    }

}
