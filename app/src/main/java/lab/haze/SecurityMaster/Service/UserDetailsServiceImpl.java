package lab.haze.SecurityMaster.Service;

import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lab.haze.SecurityMaster.Model.User;
import lab.haze.SecurityMaster.Repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException{
        User user = userRepository.getOne(id);
        System.out.println(user);
        return user;
    }
    
}
