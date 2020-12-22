package com.alokit.participate.core.security.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "secure.ignored")
public class IgnoreUrlsConfig {
	private List<String> urls = new ArrayList<String>();

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}
}
