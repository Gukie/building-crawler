package com.lokia.building.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

public class HttpClientService {

	private HttpClient httpClient = HttpClients.createDefault();
	
	public void doGet(String targetUrl) {
		HttpGet httpGet = new HttpGet(targetUrl);
		try {
			HttpResponse response = httpClient.execute(httpGet);
			
			StatusLine statusLine = response.getStatusLine();
			if(statusLine.getStatusCode()  != 200) {
				System.err.println("Failed");
			}else {
				System.out.println("HttpClientService.doGet()");
			}
			HttpEntity entity = response.getEntity();
			InputStream inputStream = entity.getContent();
			readContent(inputStream);
			 
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void readContent(InputStream inputStream) throws IOException {
		BufferedReader rd = new BufferedReader(
				new InputStreamReader(inputStream));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line).append("\n");
			}
			
			System.out.println(result);
			System.out.println("end....");
	}
}
