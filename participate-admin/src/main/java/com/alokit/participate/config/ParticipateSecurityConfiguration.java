package com.alokit.participate.config;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.alokit.participate.core.security.component.DynamicSecurityService;
import com.alokit.participate.core.security.config.WebSecurityConfig;
import com.alokit.participate.model.Resource;
import com.alokit.participate.service.AdminService;
import com.alokit.participate.service.ResourceService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ParticipateSecurityConfiguration extends WebSecurityConfig {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private ResourceService resourceService;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return username -> adminService.loadUserByUsername(username);
	}
	
	@Bean
	public DynamicSecurityService dynamicSecurityService() {
        return new DynamicSecurityService() {
        	@Override
            public Map<String, ConfigAttribute> loadDataSource() {
                Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
                List<Resource> resourceList = resourceService.listAll();
                for (Resource resource : resourceList) {
                    map.put(resource.getUrl(), new SecurityConfig(resource.getId() + ":" + resource.getName()));
                }
                return map;
            }
        };
    }
}
