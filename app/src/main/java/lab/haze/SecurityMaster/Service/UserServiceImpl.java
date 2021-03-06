package lab.haze.SecurityMaster.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lab.haze.SecurityMaster.Repository.UserRepository;
import lab.haze.SecurityMaster.Model.User;
import lab.haze.SecurityMaster.Service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
    
    public List<User> getRanking() {
        return userRepository.findAll(
            Sort.by(Sort.Direction.DESC,"companyWorth")
        );
    }
    
    public User getUser(String id){
        return userRepository.getById(id);
    }
    
    public void updateWorth(User user) {
        userRepository.save(user);
    }
}
