package com.infosys.stg.doc.model;

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
}
