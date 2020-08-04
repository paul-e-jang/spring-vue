package bashpound.marketplace.web.apis.authenticate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bashpound.marketplace.domain.model.user.SimpleUser;
import bashpound.marketplace.domain.model.user.UserRepository;
import bashpound.marketplace.domain.model.user.Users;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepository.findByUsername(username);
		return new SimpleUser(user);
	}

	
}
