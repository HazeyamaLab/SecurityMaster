package lab.haze.SecurityMaster.Repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lab.haze.SecurityMaster.Model.BadgeTimeline;
import lab.haze.SecurityMaster.Model.UserBadge;

@Repository
public interface BadgeTimelineRepository extends JpaRepository<BadgeTimeline, String> {
    List<BadgeTimeline> findAll();
    
}
