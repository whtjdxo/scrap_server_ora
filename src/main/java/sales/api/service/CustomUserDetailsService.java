package sales.api.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sales.api.dto.CustomUserDetails;
import sales.api.entity.TscUser;
import sales.api.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository  = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        TscUser userData = userRepository.findByUserId(userId);
        if(userData != null){
            return new CustomUserDetails(userData);
        }
        return null;
    }
}
