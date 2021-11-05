package lab.haze.SecurityMaster.Service;

import org.apache.catalina.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lab.haze.SecurityMaster.Model.UserBadge;
import lab.haze.SecurityMaster.Repository.UserBadgeRepository;
import lab.haze.SecurityMaster.Repository.UserRepository;

@Service
public class UserBadgeServiceImpl implements UserBadgeService {
    private final UserBadgeRepository userBadgeRepository;
    
    @Autowired
    public UserBadgeServiceImpl(UserBadgeRepository userBadgeRepository) {
        this.userBadgeRepository = userBadgeRepository;

    }
    
    public UserBadge getUserBadge(String id) {
        return userBadgeRepository.getOne(id);
    }
    
}
