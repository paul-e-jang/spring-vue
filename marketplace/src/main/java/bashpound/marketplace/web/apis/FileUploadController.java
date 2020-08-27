package bashpound.marketplace.web.apis;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class FileUploadController extends AbstractBaseController {
	
	
	public FileUploadController() {
		super();
	}
	
	@RequestMapping(value="/api/upload", produces= MediaType.APPLICATION_JSON_VALUE)
    public String fileUpload(@RequestParam("upload") MultipartFile file, Model m, @Value("${app.upload-dir}") String path) {
	
	try {
  	  String originalFileName = file.getOriginalFilename();
	  File dest = new File(path+originalFileName);
	  System.out.println(path);
	  file.transferTo(dest);
	  m.addAttribute("uploaded", 1);
	  m.addAttribute("fileName", file.getOriginalFilename());
	  m.addAttribute("url", dest);
	  
	  }catch (IllegalStateException e1) {
		e1.printStackTrace();
	  } catch (IOException e1) {
		e1.printStackTrace();
	  }
		return "toJson";
		
    }
	
}
