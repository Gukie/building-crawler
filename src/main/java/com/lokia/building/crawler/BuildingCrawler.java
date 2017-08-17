package com.lokia.building.crawler;

import com.lokia.building.service.HttpClientService;

public class BuildingCrawler implements Runnable{

	private String targetUrl;
	
	public BuildingCrawler(String targetUrl) {
		setTargetUrl(targetUrl);
	}

	private void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}
	public void run() {
		HttpClientService clientService = new HttpClientService();
		clientService.doGet(targetUrl);
	}

}
