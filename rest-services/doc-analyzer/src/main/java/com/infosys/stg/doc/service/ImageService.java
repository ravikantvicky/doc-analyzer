package com.infosys.stg.doc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.infosys.stg.doc.Utils.ImageUtils;
import com.infosys.stg.doc.repository.ImageRepository;





@Service
public class ImageService {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	ImageRepository imageRepository;


	public byte[] getImage(long imageId)  {
		try {
			return ImageUtils.base64ToImage(imageRepository.fetchImage(imageId));
		} catch (Exception e) {
			throw e;
		}
	}
}
