package lab.haze.SecurityMaster.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "user_badges")
@Data
public class UserBadge {
    @Id
    private String id;
    private boolean badge1;
    private boolean badge2;
    private boolean badge3;
    private boolean badge4;
    private boolean badge5;
    private boolean badge6;
    private boolean badge7;
    private boolean badge8;
    private boolean badge9;
    private boolean badge10;

}
