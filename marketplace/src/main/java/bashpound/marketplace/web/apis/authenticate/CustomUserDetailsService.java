package bashpound.marketplace.web.apis.authenticate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bashpound.marketplace.domain.common.model.Role;
import bashpound.marketplace.domain.model.user.SimpleUser;
import bashpound.marketplace.domain.model.user.UserRepository;
import bashpound.marketplace.domain.model.user.Users;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Users> userEntityWrapper = Optional.ofNullable(userRepository.findByUsername(username));
		Users user = userEntityWrapper.get();
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		if(("eunhackjang").equals(username)) {
			authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
		}else {
			authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
		}
		return new SimpleUser(user);
	}

	
}
