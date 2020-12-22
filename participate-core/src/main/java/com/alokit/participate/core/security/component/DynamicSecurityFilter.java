package com.alokit.participate.core.security.component;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.alokit.participate.core.security.config.IgnoreUrlsConfig;

public class DynamicSecurityFilter extends AbstractSecurityInterceptor implements Filter {

	@Autowired
	private IgnoreUrlsConfig ignoreUrlsConfig;
	
	@Autowired
    private DynamicSecurityMetadataSource dynamicSecurityMetadataSource;
	
	@Autowired
	public void setMyAccessDecisionManager(DynamicAccessDecisionManager dynamicAccessDecisionManager) {
		super.setAccessDecisionManager(dynamicAccessDecisionManager);
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		FilterInvocation filterInvocation = new FilterInvocation(servletRequest, servletResponse, filterChain);
		if (request.getMethod().equals(HttpMethod.OPTIONS.toString())) {
			filterInvocation.getChain().doFilter(filterInvocation.getRequest(), filterInvocation.getResponse());
			return;
		}

		PathMatcher pathMatcher = new AntPathMatcher();
		for (String path : ignoreUrlsConfig.getUrls()) {
			if (pathMatcher.match(path, request.getRequestURI())) {
				filterInvocation.getChain().doFilter(filterInvocation.getRequest(), filterInvocation.getResponse());
				return;
			}
		}
		
		InterceptorStatusToken token = super.beforeInvocation(filterInvocation);
		
		try {
			filterInvocation.getChain().doFilter(filterInvocation.getRequest(), filterInvocation.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
	}

	@Override
	public Class<?> getSecureObjectClass() {
		 return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return dynamicSecurityMetadataSource;
	}

}
