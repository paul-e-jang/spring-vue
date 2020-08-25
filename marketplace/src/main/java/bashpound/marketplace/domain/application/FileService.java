package bashpound.marketplace.domain.application;

import org.springframework.web.multipart.MultipartFile;

import bashpound.marketplace.infrastructure.file.FileStorageException;

public interface FileService {
	
	/**
	 * 
	 * @param upload multipartFile. expects uploaded file on local storage. 
	 */
	void fileUpload(MultipartFile multipartFile) throws FileStorageException;
	
}
