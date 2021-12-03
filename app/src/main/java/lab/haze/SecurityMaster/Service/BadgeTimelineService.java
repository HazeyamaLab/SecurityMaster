package lab.haze.SecurityMaster.Service;

import java.util.List;

import lab.haze.SecurityMaster.Model.BadgeTimeline;
import lab.haze.SecurityMaster.Model.UserBadge;

public interface BadgeTimelineService {
    List<BadgeTimeline> getAll();
}
