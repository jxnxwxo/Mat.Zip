package com.project.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.service.SearchService;
import com.project.vo.DataVO;
import com.project.vo.PagingVO;
import com.project.vo.RestaurantVO;

@RestController
public class SearchController {

	@Autowired
	SearchService searchService;

	// 검색 결과
	@PostMapping("/searchList")
	public DataVO searchList(RestaurantVO restaurantVO, PagingVO pagingVO) throws Exception {

		//System.out.println(restaurantVO.toString());
		//System.out.println(pagingVO.toString());

		return searchService.searchList(restaurantVO, pagingVO);
	}

	// Naver STT
	@PostMapping("/sttService")
	public String sttService(MultipartFile file) {
		
		String clientId = "zcdg10z5u8"; // Application Client ID";
		String clientSecret = "XijlJdO9u3KzYxNN1PKAkcLhUp2jIUgyi1TGaPk7"; // Application Client Secret"

		DataOutputStream dos = null;
		BufferedReader br = null;
		StringBuilder sum = null;

		try {
			String language = "Kor";
			String apiURL = "https://naveropenapi.apigw.ntruss.com/recog/v1/stt?lang=" + language;
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setUseCaches(false);
			con.setDoOutput(true);
			con.setDoInput(true);

			con.setRequestProperty("Content-Type", "application/octet-stream");
			con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
			con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);

			OutputStream outputStream = con.getOutputStream();
			
			file.transferTo(new File("/home/ubuntu/temp/sample.wav"));
			File voiceFile = new File("/home/ubuntu/temp/sample.wav");
			//file.transferTo(voiceFile);
			FileInputStream inputStream = new FileInputStream(voiceFile);
			byte[] buffer = new byte[4096];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
			outputStream.flush();
			inputStream.close(); // sample.wav 같은 음성파일 전송 완료

			br = new BufferedReader(new InputStreamReader(con.getInputStream()));

			String oneLine = "";
			sum = new StringBuilder();
			while ((oneLine = br.readLine()) != null) {
				sum.append(oneLine);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
				if (dos != null)
					dos.close();
			} catch (Exception e) {

			}
		}
		
		JSONObject json = null;
		try {
			JSONParser parser = new JSONParser();
			json = (JSONObject) parser.parse(sum.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(json.toJSONString());
		
		return json.toJSONString();
	}
}
