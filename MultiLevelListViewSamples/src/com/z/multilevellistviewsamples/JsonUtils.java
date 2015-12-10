package com.z.multilevellistviewsamples;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

public class JsonUtils {
	
	public static List<Map<String, Object>> getListMap(String json)throws Exception{
		return JSON.parseObject(json,new TypeReference<List<Map<String, Object>>>(){});
	}
	
	public static Map<String, Object> getMapObj(String json)throws Exception{
		return JSON.parseObject(json, new TypeReference<Map<String, Object>>(){});
	}
}
