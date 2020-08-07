package bashpound.marketplace.web.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

  @GetMapping(value = { "/", "/loginpage" })
  public String getEntry() {
    return "index";
  }
  
  @PostMapping(value = { "/", "/loginpage" })
  public String postEntry() {
    return "index";
  }
}
