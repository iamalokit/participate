package com.alokit.participate.core.util;

import java.net.URI;
import java.net.URISyntaxException;

public class URLUtil {
	public static String getPath(String uriStr) {
		URI uri;
		try {
			uri = new URI(uriStr);
		} catch (URISyntaxException e) {
			throw new UtilException(e);
		}
		return uri.getPath();
	}
}
