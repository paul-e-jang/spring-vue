package bashpound.marketplace.domain.application.impl;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import bashpound.marketplace.infrastructure.file.FileStorageException;

@Service
public class FileServiceImpl {
	
    @Value("${app.uploadDir:${user.home}}")
    private String uploadDir;

    public void fileUpload(MultipartFile file) throws Exception {
    	

         
    	}
}

