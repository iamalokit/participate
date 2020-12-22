package com.alokit.participate.core.security.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.alokit.participate.core.util.URLUtil;

public class DynamicSecurityMetadataSource implements FilterInvocationSecurityMetadataSource{
	
	private static Map<String, ConfigAttribute> configAttributeMap = null;
	
	@Autowired
    private DynamicSecurityService dynamicSecurityService;
	
	@PostConstruct
    public void loadDataSource() {
        configAttributeMap = dynamicSecurityService.loadDataSource();
    }
	
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		if (configAttributeMap == null) 
			this.loadDataSource();
		List<ConfigAttribute>  configAttributes = new ArrayList<>();
		String url = ((FilterInvocation) object).getRequestUrl();
		String path = URLUtil.getPath(url);
		PathMatcher pathMatcher = new AntPathMatcher();
		Iterator<String> iterator = configAttributeMap.keySet().iterator();
		while (iterator.hasNext()) {
            String pattern = iterator.next();
            if (pathMatcher.match(pattern, path)) {
                configAttributes.add(configAttributeMap.get(pattern));
            }
        }
        return configAttributes;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
