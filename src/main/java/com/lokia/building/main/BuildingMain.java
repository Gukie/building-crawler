package com.lokia.building.main;

import com.lokia.building.service.BuildingCrawlerService;

public class BuildingMain {
	
	

	public static void main(String[] args) {
		
		BuildingCrawlerService buildingCrawlerService = new BuildingCrawlerService();
//		buildingCrawlerService.crawlLianjia();
		buildingCrawlerService.crawlZhujiayi();
	}


	


}
