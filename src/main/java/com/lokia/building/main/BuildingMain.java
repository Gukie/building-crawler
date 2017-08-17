package com.lokia.building.main;

import com.lokia.building.crawler.LianjiaCrawler;

public class BuildingMain {

	public static void main(String[] args) {
		
		String targetUrl = "http://hz.fang.lianjia.com/loupan/";
		
//		BuildingCrawler crawler = new BuildingCrawler(targetUrl);
//		Thread thread = new Thread(crawler);
//		thread.start();
		
		LianjiaCrawler lianjiaCrawler = new LianjiaCrawler(targetUrl);
		int totalPage = lianjiaCrawler.crawlTotalPage();
		System.err.println("total pages:"+totalPage);
		if(totalPage>0) {
			String subUrl = lianjiaCrawler.getSubUrl();
			for(int i = 0 ;i<totalPage;i++) {
				LianjiaCrawler crawler = new LianjiaCrawler(targetUrl+subUrl+i);
				Thread thread = new Thread(crawler);
				thread.start();
			}
		}
		
	}

}
