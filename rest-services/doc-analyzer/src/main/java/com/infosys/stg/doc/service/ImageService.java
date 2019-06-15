package com.infosys.stg.doc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.stg.doc.Utils.ImageUtils;
import com.infosys.stg.doc.repository.ImageRepository;

@Service
public class ImageService {
	@Autowired
	ImageRepository imageRepository;

	public byte[] getImage(long imageId) {
		try {
			return ImageUtils.base64ToImage(imageRepository.fetchImage(imageId));
		} catch (Exception e) {
			throw e;
		}
	}
}
