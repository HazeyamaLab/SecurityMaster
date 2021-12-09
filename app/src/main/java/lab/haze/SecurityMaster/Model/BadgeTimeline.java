package lab.haze.SecurityMaster.Model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "badge_timelines")
@Data
public class BadgeTimeline {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String userId;
    private int badgeId;
    private String ltd;

}
