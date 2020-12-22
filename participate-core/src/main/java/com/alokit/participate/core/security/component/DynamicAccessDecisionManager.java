package com.alokit.participate.core.security.component;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class DynamicAccessDecisionManager implements AccessDecisionManager{

	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		if(configAttributes != null && !configAttributes.isEmpty()) {
			Iterator<ConfigAttribute> iterator = configAttributes.iterator();
			while (iterator.hasNext()) {
				ConfigAttribute configAttribute = iterator.next();
				String needAuthority = configAttribute.getAttribute();
				for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
					if (needAuthority.trim().equals(grantedAuthority.getAuthority())) {
	                    return;
	                }
				}
				
			}
		}
		
		throw new AccessDeniedException("Access Denied");
		
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}
	
}
