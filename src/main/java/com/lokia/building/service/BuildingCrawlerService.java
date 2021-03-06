package com.lokia.building.service;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.lokia.building.crawler.CrawlerTask;
import com.lokia.building.crawler.impl.LianjiaCrawler;
import com.lokia.building.crawler.impl.ZhujiayiCrawler;
import com.lokia.building.utils.CrawlerUtils;

public class BuildingCrawlerService {
	
	public void crawlLianjia() {
		String targetUrl = CrawlerUtils.crawlClassTargetUrlMap.get(LianjiaCrawler.class);
		CrawlerTask lianjiaCrawler = new LianjiaCrawler(targetUrl);
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

	public void crawlZhujiayi() {
		
		int threadNum = Runtime.getRuntime().availableProcessors()+1;
		Executor executor = Executors.newFixedThreadPool(threadNum);
			
//		ExecutorCompletionService<CrawlerTask> executorCompletionService = new ExecutorCompletionService<CrawlerTask>(executor);
		
		String targetUrl = CrawlerUtils.crawlClassTargetUrlMap.get(ZhujiayiCrawler.class);
		ZhujiayiCrawler crawler  = new ZhujiayiCrawler(targetUrl);
		int totalPage = crawler.crawlTotalPage();
		
		if(totalPage>0) {
			String subUrl = crawler.getSubUrl();
			for(int i = 0 ;i<=totalPage;i++) {
				crawler = new ZhujiayiCrawler(targetUrl+subUrl+i);
				executor.execute(crawler);
			}
				
		}
		
		System.out.println("end...");
		
	}
}
