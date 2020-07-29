package bashpound.portfolio.marketplace;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping(value= {"/", "/loginpage"})
	public String entry() {
		return "index";
	}

}
