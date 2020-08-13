package bashpound.marketplace.web.apis;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import bashpound.marketplace.domain.common.security.CurrentUser;
import bashpound.marketplace.domain.model.user.SimpleUser;

@Controller
public class LoginSessionController {
	

    @RequestMapping(value="/api/user", produces= MediaType.APPLICATION_JSON_VALUE)
    public String getCurrentMemberJson(Model model, @CurrentUser SimpleUser user) {
      	
    	String currentUser = "Guest";
    	
    	if (user != null) {
    		currentUser = user.getUsername();
        	model.addAttribute("user", currentUser);
            model.addAttribute("usercode", currentUser.hashCode());
            model.addAttribute("authenticated", true);
    	}
        
        return "jsonmembertemplate";
    }

}
