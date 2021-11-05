package lab.haze.SecurityMaster.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lab.haze.SecurityMaster.Model.UserBadge;

@Repository
public interface UserBadgeRepository extends JpaRepository<UserBadge, String> {
    UserBadge getOne(String id);
    
}
