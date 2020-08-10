package bashpound.marketplace.web.apis;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import bashpound.marketplace.domain.common.security.CurrentUser;
import bashpound.marketplace.domain.model.user.SimpleUser;

@Controller
public class ArticleController {
    @RequestMapping(value="/api/article", produces= MediaType.APPLICATION_JSON_VALUE)
    public String getCurrentMemberJson(Model model, @CurrentUser SimpleUser user) {
      	
    	String currentuser = "Guest";
    	if (user != null) {
    		currentuser = user.getUsername();
    	}
    	
    	model.addAttribute("user", currentuser);
        
        boolean authenticated = false;
        if (user.getUsername() != null) {
        	authenticated = true;
        }
        
        model.addAttribute("authenticated", authenticated);
        
        return "jsonmembertemplate";
    }
}
