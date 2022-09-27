package com.example.demo.config;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@KeycloakConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class keyCloakConfig extends KeycloakWebSecurityConfigurerAdapter {

	//Configuración otorgada por la documentación https://www.keycloak.org/docs/latest/securing_apps/#_spring_security_adapter
	 @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		 	KeycloakAuthenticationProvider provider = new KeycloakAuthenticationProvider();
		 	provider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
	        auth.authenticationProvider(provider);
	    }
	
	@Bean
	@Override
	protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
		return new NullAuthenticatedSessionStrategy();
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception
    {
        super.configure(http);
        http
                .authorizeRequests()
                .anyRequest().permitAll();
        http.csrf().disable();
    }
	
	//Le indicamos donde buscar las configuraciones y credenciales (application.properties
	@Bean
	public KeycloakConfigResolver keycloakConfigResolver() {	
		return new KeycloakSpringBootConfigResolver();
	}
	
	

}
