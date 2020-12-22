package com.alokit.participate.core.security.component;

import java.util.Map;

import org.springframework.security.access.ConfigAttribute;

public interface DynamicSecurityService {

	Map<String, ConfigAttribute> loadDataSource();
}
