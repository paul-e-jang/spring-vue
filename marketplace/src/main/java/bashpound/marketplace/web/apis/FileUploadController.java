package bashpound.marketplace.web.apis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


@Controller
public class FileUploadController extends AbstractBaseController {
	private final Path fileLocation;
	
	public FileUploadController() {
		super();
		this.fileLocation = Paths.get("./src/main/resources/files/").toAbsolutePath().normalize();
	}
	
	@RequestMapping(value="/api/upload", produces= MediaType.APPLICATION_JSON_VALUE)
    public String fileUpload(@RequestParam("upload") MultipartFile file, MultipartHttpServletRequest request, HttpSession session, Model m) {
	
	try {
	  Files.createDirectories(this.fileLocation);
	  
  	  String originalFileName = file.getOriginalFilename();
  	  Path targetLocation = this.fileLocation.resolve(originalFileName);
  	  Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
	  m.addAttribute("uploaded", 1);
	  m.addAttribute("fileName", file.getOriginalFilename());
	  m.addAttribute("url", targetLocation);
	  
	  }catch (IllegalStateException e1) {
		e1.printStackTrace();
	  } catch (IOException e1) {
		e1.printStackTrace();
	  }
		return "toJson";
		
    }
	
}
