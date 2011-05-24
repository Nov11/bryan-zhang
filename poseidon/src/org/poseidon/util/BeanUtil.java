package org.poseidon.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BeanUtil {

	public static <T> void map2Bean(Map<String, Object> map, T t) {
		Object[] objKeys = map.keySet().toArray();
		String strFieldName = null;
		try {
			for (Object objkey : objKeys) {
				strFieldName = objkey.toString();
				Field objField = t.getClass().getDeclaredField(strFieldName);
				objField.setAccessible(true);
				objField.set(t, map.get(strFieldName));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static <T> Map<String, Object> bean2Map(T t) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Field[] fieldArray = t.getClass().getDeclaredFields();
		for (Field f : fieldArray) {
			f.setAccessible(true);
			try {
				if (f.getName().equals("serialVersionUID")){
					continue;
				}
				returnMap.put(f.getName(), f.get(t));
			} catch (Exception e) {
				e.printStackTrace();
				returnMap = null;
			}
		}
		return returnMap;
	}
}
