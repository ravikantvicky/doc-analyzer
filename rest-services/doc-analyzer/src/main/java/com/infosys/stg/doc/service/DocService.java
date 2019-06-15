package com.infosys.stg.doc.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.infosys.stg.doc.exception.DocAnalyzeException;
import com.infosys.stg.doc.model.Pdf2ImageResponse;

@Service
public class DocService {
	@Autowired
	private Environment env;

	public Pdf2ImageResponse pdf2Image(MultipartFile file) throws DocAnalyzeException {
		try {
			MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
			bodyMap.add("file", file.getResource());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Pdf2ImageResponse> resp = restTemplate.exchange(env.getProperty("url.pdf2Image"),
					HttpMethod.POST, requestEntity, Pdf2ImageResponse.class);
			return resp.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DocAnalyzeException("Error in Pdf2Image conversion.");
		}
	}

	public String scanBarCode(String imageData) {
		return null;
	}

	public String alignImage(String rawImage, String refImage) {
		try {
			Map<String, String> request = new HashMap<>();
			request.put("raw_image", rawImage);
			request.put("ref_image", refImage);

			RestTemplate restTemplate = new RestTemplate();
			@SuppressWarnings("unchecked")
			Map<String, String> response = restTemplate.postForObject(env.getProperty(""), request, Map.class,
					new Object());
			if (response != null && response.containsKey("aligned_img"))
				return response.get("aligned_img");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return rawImage;
		}
	}
}
