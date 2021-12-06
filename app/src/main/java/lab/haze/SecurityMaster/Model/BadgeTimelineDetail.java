package lab.haze.SecurityMaster.Model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "badge_timelines")
@Data
public class BadgeTimelineDetail {
    @Id
    private int id;
    private String userName;
    private String ltd;
    private String badgeName;

}
