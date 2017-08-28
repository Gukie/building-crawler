package com.lokia.building.crawler.impl;

import java.util.Iterator;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.lokia.building.bean.BuildingPageInfo;
import com.lokia.building.crawler.CrawlerTask;

public class ZhujiayiCrawler extends AbstractCrawler implements CrawlerTask , Runnable{
	
	String targetElement = ".bshadow.lpMode";
	
	String subUrl = "all_";
	
	private String targetUrl;

	public ZhujiayiCrawler(String targetUrl) {
		setTargetUrl(targetUrl);
	}

	@Override
	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}
	
	public String getSubUrl() {
		return subUrl;
	}
	
	@Override
	public String getTargetElement() {
		return targetElement;
	}
	
	public void run() {
		doCrawl();
	}


	@Override
	public Elements parseAveragePrice(Element element) {
		return element.select(".bshadow.lpMode").select(".fl.lptool").select(".lp_price");
	}

	@Override
	public Elements parseArea(Element element) {

		Elements elements = element.select(".bshadow.lpMode").select(".fl.lpInfo").select(".lpdata").select(".lp_d_line");
		if(elements.size() > 2) {
			Elements spanElements = elements.get(2).select("span");
			
			if(spanElements.size()>1) {
				return spanElements.get(1).getAllElements();
			}
		}
		return null;
	
	}

	@Override
	public Elements parseLocation(Element element) {
		Elements elements = element.select(".bshadow.lpMode").select(".fl.lpInfo").select(".lpdata").select(".lp_d_line");
		if(elements.size() > 1) {
			Elements spanElements = elements.get(1).select("span");
			
			if(spanElements.size()>1) {
				return spanElements.get(1).children();
			}
		}
		return null;
	}

	@Override
	public Elements parseBuildingName(Element element) {
		return element.select(".bshadow.lpMode").select(".fl.lpInfo").select(".lpName").select("a");
	}

	@Override
	BuildingPageInfo parsePageInfo(Document document) {
		Elements pageInfoEle = document.select("div.pager").select("ul").select("li.last").select("a");// <div class="page-box house-lst-page-box"></div
		Iterator<Element> iterator = pageInfoEle.iterator();
		if(iterator.hasNext()) {
			Element page = iterator.next();
			String totalPage = page.attr("data-page");
			BuildingPageInfo result = new BuildingPageInfo();
			result.setTotalPage(totalPage);
			return result;
		}
		return null;
	}

	

}
