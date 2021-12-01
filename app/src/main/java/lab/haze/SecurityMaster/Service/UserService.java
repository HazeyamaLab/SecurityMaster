package lab.haze.SecurityMaster.Service;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lab.haze.SecurityMaster.Model.User;

public interface UserService{
    List<User> getUsers();

    List<User> getRanking();
    
    User getUser(String id);

    void updateWorth(User user);
}
