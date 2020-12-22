package com.alokit.participate.core.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.alokit.participate.core.security.component.DynamicAccessDecisionManager;
import com.alokit.participate.core.security.component.DynamicSecurityFilter;
import com.alokit.participate.core.security.component.DynamicSecurityMetadataSource;
import com.alokit.participate.core.security.component.DynamicSecurityService;
import com.alokit.participate.core.security.component.JwtAuthenticationTokenFilter;
import com.alokit.participate.core.security.component.RestAuthenticationEntryPoint;
import com.alokit.participate.core.security.component.RestfulAccessDeniedHandler;
import com.alokit.participate.core.security.util.JwtTokenUtil;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired(required = false)
	private DynamicSecurityService dynamicSecurityService;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity.authorizeRequests();
		for(String url: ignoreUrlsConfig().getUrls()) {
			registry.antMatchers(url).permitAll();
		}
		
		registry.and()
		.authorizeRequests()
		.anyRequest()
		.authenticated()
		.and()
		.csrf()
		.disable()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.exceptionHandling()
		.accessDeniedHandler(restfulAccessDeniedHandler())
		.authenticationEntryPoint(restAuthenticationEntryPoint())
		.and()
		.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		
		if(dynamicSecurityService != null) {
			registry.and().addFilterBefore(dynamicSecurityFilter(), FilterSecurityInterceptor.class);
		}
		
	}
	
	@Bean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil();
    }
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Bean
	public IgnoreUrlsConfig ignoreUrlsConfig() {
		return new IgnoreUrlsConfig();
	}

	@Bean
	public RestfulAccessDeniedHandler restfulAccessDeniedHandler() {
		return new RestfulAccessDeniedHandler();
	}

	@Bean
	public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
		return new RestAuthenticationEntryPoint();
	}
	
	@Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }
	
	@ConditionalOnBean(name = "dynamicSecurityService")
    @Bean
    public DynamicSecurityFilter dynamicSecurityFilter() {
        return new DynamicSecurityFilter();
    }
	

    @ConditionalOnBean(name = "dynamicSecurityService")
    @Bean
    public DynamicSecurityMetadataSource dynamicSecurityMetadataSource() {
        return new DynamicSecurityMetadataSource();
    }
    
    @ConditionalOnBean(name = "dynamicSecurityService")
    @Bean
    public DynamicAccessDecisionManager dynamicAccessDecisionManager() {
        return new DynamicAccessDecisionManager();
    }
}
