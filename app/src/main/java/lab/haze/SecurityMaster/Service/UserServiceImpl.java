package lab.haze.SecurityMaster.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<User> getUsers(){
        return userRepository.findAll();
    }
}
