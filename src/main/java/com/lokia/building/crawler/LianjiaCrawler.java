package com.lokia.building.crawler;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;

/**
 * refer: 
 * <li> https://www.ibm.com/developerworks/cn/java/j-lo-jsouphtml/index.html
 * 
 * @author lokia
 *
 */
public class LianjiaCrawler implements Runnable {

	
	String targetElement = ".info-panel";
	String subUrl = "/loupan/pg";
	
	private String targetUrl;

	public LianjiaCrawler(String targetUrl) {
		setTargetUrl(targetUrl);
	}
	
	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}
	
	public String getSubUrl() {
		return subUrl;
	}

	public void setSubUrl(String subUrl) {
		this.subUrl = subUrl;
	}
	
	public int crawlTotalPage() {
		
		PageInfo pageInfo = null;
		try {
			Document document = Jsoup.connect(targetUrl).get();
			pageInfo = parsePageInfo(document);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pageInfo==null?0:Integer.parseInt(pageInfo.totalPage);
	}
	
	
	private String crawl() {
		
		try {
			Document document = Jsoup.connect(targetUrl).get();
			parseCurrentPageInfo(document);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	private PageInfo parsePageInfo(Document document) {
		Elements pageInfoEle = document.select("div.page-box.house-lst-page-box");// <div class="page-box house-lst-page-box"></div
		Iterator<Element> iterator = pageInfoEle.iterator();
		if(iterator.hasNext()) {
			Element page = iterator.next();
			String pageTotalInfo = page.attr("page-data");
			return JSON.parseObject(pageTotalInfo, PageInfo.class);
		}
		return null;
	}


	private void parseCurrentPageInfo(Document document) {
		Elements elementList = document.select(targetElement);
		Iterator<Element> iterator = elementList.iterator();
		while(iterator.hasNext()) {
			Element element = iterator.next();
			parseElement(element);
		}
	}
	
	
	private void parseElement(Element element) {
		String buildingName = parseBuildingName(element);
		String location = parseLocation(element);
		String area = parseArea(element);
		String average= parseAveragePrice(element);
		
		System.out.println(buildingName+","+location+","+area+","+average);
	}


	private String parseAveragePrice(Element element) {
		Elements averagePrice = element.select(".col-2 .price .average .num");
		return getElementsText(averagePrice);
	}


	private String parseArea(Element element) {
		Elements area = element.select(".col-1 .area");
		return getElementsText(area);
	}


	private String parseLocation(Element element) {
		Elements location = element.select(".col-1 .where .region");
		return getElementsText(location);
	}


	private String parseBuildingName(Element element) {
		Elements headers = element.select("h2");
		return getElementsText(headers);
	}


	private String getElementsText(Elements elements) {
		Iterator<Element> iterator = elements.iterator();
		if(iterator.hasNext()) {
			Element header = iterator.next();
			return header.text();
		}
		return "";
	}


	public void run() {
		crawl();
	}
	
	static class PageInfo{
		private String totalPage;
		private String curPage;
		
		public String getCurPage() {
			return curPage;
		}
		public void setCurPage(String curPage) {
			this.curPage = curPage;
		}
		public String getTotalPage() {
			return totalPage;
		}
		public void setTotalPage(String totalPage) {
			this.totalPage = totalPage;
		}
		
	}
}
