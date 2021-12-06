package lab.haze.SecurityMaster.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lab.haze.SecurityMaster.Model.BadgeTimeline;
import lab.haze.SecurityMaster.Model.UserBadge;
import lab.haze.SecurityMaster.Repository.BadgeTimelineRepository;

@Service
public class BadgeTimelineServiceImpl implements BadgeTimelineService {

    @Autowired
    private BadgeTimelineRepository badgeTimelineRepository;

    @Override
    @Transactional
    public List<BadgeTimeline> getAll(){
        List<BadgeTimeline> list = badgeTimelineRepository.findAll(
            Sort.by(Sort.Direction.DESC,"ltd")
        );
        return list;
    }
    
}