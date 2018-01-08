package com.batata.authentication.security;

import com.batata.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * this is a example how to create a filter to authenticates a user using a user in datasource (defined in User class)
 */
@Configuration
@EnableAuthorizationServer // this will indicate springs to act like a authentication service
public class AuthServerOAuth2Config extends AuthorizationServerConfigurerAdapter {

    //token properties
    @Value("${auth.client.secret}")
    private String clientKey;
    @Value("${auth.client.id}")
    private String clientId;
    @Value("#{'${auth.client.scope}'.split(',')}") //creates a array
    private String[] scope;
    @Value("#{'${auth.client.authorized-grant-types}'.split(',')}")
    private String[] grantTypes;
    @Value("${auth.token.expiration.time}")
    private Integer expirationTime;
    @Value("${auth.token.validity.time}")
    private Integer validityTime;

    /**
     * class used to query in datasource
     */
    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("tokenConverterDefault")
    private TokenCustomizer customTokenConverter;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    /**
     * stores oauth token
     */
    @Autowired
    @Qualifier("tokenStoreDefault")
    private TokenStore tokenStore;

    /**
     * Define endpoint configuration for tokens requets
     * @param endpoints  Configure the properties and enhanced functionality of the Authorization Server endpoints.
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
            .tokenStore(tokenStore) //define token store
            .authenticationManager(authenticationManager) // define how to authenticate
            .userDetailsService(userService) // userService used
            .accessTokenConverter(customTokenConverter) //how to convert token
            .pathMapping("/oauth/token", "/login") // define path to generate token, default is url:port/oauth/token
        ;
    }

    /**
     * @param clients a configurer that defines the client details service. Client details can be initialized, or you can just refer to an existing store.
     * @throws Exception
     */
    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
            .withClient(clientId)                         //client sent in request
            .secret(clientKey)                            //client secret
            .scopes(scope)
            .authorizedGrantTypes(grantTypes)
            .accessTokenValiditySeconds(validityTime)
            .refreshTokenValiditySeconds(expirationTime)

        ;
    }
}

