package com.infosys.stg.doc.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.infosys.stg.doc.exception.StorageException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;


@Service
public class StorageService {


    public String uploadFile(MultipartFile file) {

        if (file.isEmpty()) {
            return "Fail";
        }
        
        else {
        	return "Success";
        }
        

    }
}
