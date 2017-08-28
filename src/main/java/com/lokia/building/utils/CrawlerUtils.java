package com.lokia.building.utils;

import java.util.HashMap;
import java.util.Map;

import com.lokia.building.crawler.impl.LianjiaCrawler;
import com.lokia.building.crawler.impl.ZhujiayiCrawler;

public class CrawlerUtils {

	
	public static Map<Class<? extends Object>, String> crawlClassTargetUrlMap = new HashMap<Class<? extends Object>, String>();
	
	static {
		crawlClassTargetUrlMap.put(LianjiaCrawler.class, "http://hz.fang.lianjia.com/loupan/");
		crawlClassTargetUrlMap.put(ZhujiayiCrawler.class, "http://hz.zje.com/loupan/");
	}
}
