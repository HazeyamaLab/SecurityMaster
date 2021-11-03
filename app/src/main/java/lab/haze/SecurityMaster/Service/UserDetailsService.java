package lab.haze.SecurityMaster.Service;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lab.haze.SecurityMaster.Model.User;

@Service
public interface UserDetailsService {
    UserDetails loadUserByUsername(String id) throws UsernameNotFoundException;
    
}
