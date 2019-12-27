package com.sofaseal.authentication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configuration.ClientDetailsServiceConfiguration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * @Description:
 *  定义哪些应用程序可以使用服务
 * @Author: JackYan
 * @Date2019/12/24 10:51
 * @Version V1.0
 **/
@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 定义哪些客户端将注册到服务
     **/
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
        // 存储方式：内存/JDBC
        clients.inMemory()
                // 注册应用程序名
                .withClient("eagleeye")
                // 秘钥；通过秘钥向oauth2换令牌
                .secret("thisissecrect")
                // 授权类型列表（密码授权类型、客户端凭据授权类型）
                .authorizedGrantTypes(
                        "refresh_token",
                        "password",
                        "client_credentials")
                // 令牌可用范围：web、手机
                .scopes("webclient","mobileclient");
    }


    /**
     * 定义AuthorizationServerConfigurer中使用哪些组件
     * 使用Spring默认的 验证管理器 和 用户详细信息服务
     **/
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).userDetailsService(userDetailsService);
    }
}
