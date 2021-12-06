package lab.haze.SecurityMaster.Service;

import java.util.List;

import lab.haze.SecurityMaster.Model.UserBadge;

public interface UserBadgeService {
    UserBadge getUserBadge(String id);
    void updateBadge(UserBadge userBadge);
    List<UserBadge> findAll();
}
