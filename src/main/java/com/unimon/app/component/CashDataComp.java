package com.unimon.app.component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.unimon.app.common.exception.AppException;

import lombok.extern.slf4j.Slf4j;

/**
 * 싱글턴 컴포넌트
 *
 */
@Slf4j
@Component(value = "CashDataComp")
public class CashDataComp {

	private static Map<String, Object> dataMap = new HashMap<>();
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getList(String key) throws RuntimeException {

		Object obj = this.getObject(key);
		
		if(obj != null && obj instanceof List) {
			return (List<Map<String, Object>>) obj;
		}
		
		return null;
	}

	public Object getObject(String key) throws RuntimeException {
		
		Object obj = dataMap.get(key);
		
		if(obj == null)
			throw new AppException("Get dataMap is Null");
		
		log.debug("Get dataMap : {}", key);
		
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getMap(String key) throws RuntimeException {

		Object obj = this.getObject(key);
		
		if(obj != null && obj instanceof Map) {
			return (Map<String, Object>) obj;
		}
		
		return null;
	}
	
	public void set(String key, Object obj) throws RuntimeException {
		log.info("dataMap set : {}", key);
		dataMap.put(key, obj);
	}

	public void remove(String key) throws Exception {
		dataMap.remove(key);
	}
	
	public boolean containsKey(String key) throws RuntimeException {
		return dataMap.containsKey(key);
	}
}
