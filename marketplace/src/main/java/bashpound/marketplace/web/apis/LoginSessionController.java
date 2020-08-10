package bashpound.marketplace.web.apis;


import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import bashpound.marketplace.domain.model.user.SimpleUser;

@Controller
public class LoginSessionController {
	

    @RequestMapping(value="/api/user", produces= MediaType.APPLICATION_JSON_VALUE)
    public String getCurrentMemberJson(Model model, @AuthenticationPrincipal SimpleUser user) {
      	
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
