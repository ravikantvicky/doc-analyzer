package com.infosys.stg.doc.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.stg.doc.service.ImageService;

@RestController
@CrossOrigin
public class ImageController {
	@Autowired
	ImageService imageService;
	
	@GetMapping("/images/{imageId}")
	public void viewImage(@PathVariable("imageId") long imageId, HttpServletResponse response) {
		try {
			byte[] imageData = imageService.getImage(imageId);
			InputStream is = new ByteArrayInputStream(imageData);
			String mimeType = URLConnection.guessContentTypeFromStream(is);
			response.setContentType(mimeType);
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(imageData);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
