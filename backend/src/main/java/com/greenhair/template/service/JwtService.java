package com.greenhair.template.service;

import java.util.Map;

public interface JwtService {
	<T> String create(String key, T data, String subject);
	Map<String, Object> get(String key);
	int getMemberId();
	boolean isUsable(String jwt);
	
}